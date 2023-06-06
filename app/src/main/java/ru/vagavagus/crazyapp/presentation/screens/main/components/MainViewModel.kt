package ru.vagavagus.crazyapp.presentation.screens.main.components

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.vagavagus.crazyapp.common.RequestResult
import ru.vagavagus.crazyapp.domain.model.MatchDetails
import ru.vagavagus.crazyapp.domain.model.MatchResultTeamInfo
import ru.vagavagus.crazyapp.domain.model.Team
import ru.vagavagus.crazyapp.domain.use_cases.FetchMatchCardListUseCase
import ru.vagavagus.crazyapp.domain.use_cases.FetchMatchDetailsUseCase
import ru.vagavagus.crazyapp.presentation.screens.main.components.common.TabItem
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchMatchCardListUseCase: FetchMatchCardListUseCase,
    private val fetchMatchDetailsUseCase: FetchMatchDetailsUseCase
): ViewModel() {

    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Loading)
    val state: StateFlow<MainScreenState> = _state

    val selectedItems = mutableStateListOf<MatchResultTeamInfo>()
    init {
        viewModelScope.launch {
            onEvent(event = MainScreenEvent.FetchMatchCardList)
            delay(1000)
            onEvent(event = MainScreenEvent.FetchMatchDetails(TabItem.Football.value))
        }
    }

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.FetchMatchCardList -> fetchMatchCardList()
            is MainScreenEvent.FetchMatchDetails -> fetchMatchDetails(event.type)
            is MainScreenEvent.ChangeSelectedItemsCount -> changeSelectedItems(event.details, event.factor, event.isSelected, event.winner)
            is MainScreenEvent.ClearAllSelectedItems -> clearItems()
            is MainScreenEvent.ClearSelectedItem -> clearSelectedItem(event.item)
        }
    }

    private fun clearItems() {
        selectedItems.clear()
    }

    private fun clearSelectedItem(item: MatchResultTeamInfo) {
        selectedItems.remove(item)
    }

    private fun changeSelectedItems(
        details: MatchDetails,
        factor: Float,
        isSelected: Boolean,
        winner: Team?
    ) {
        if(isSelected) {
            val matchResult = MatchResultTeamInfo(
                detailsId = details.id,
                team1 = details.team1,
                team2 = details.team2,
                winner = winner,
                factor = factor
            )
            selectedItems.add(matchResult)
        }
        else {
            val item = selectedItems.find { it.detailsId == details.id }
            selectedItems.remove(item)
        }
    }

    private fun fetchMatchCardList() = viewModelScope.launch(Dispatchers.IO) {
        fetchMatchCardListUseCase().collect { result ->
            when(result) {
                is RequestResult.Loading -> _state.update { MainScreenState.Loading }
                is RequestResult.Error -> _state.update { MainScreenState.Error(result.message!!) }
                is RequestResult.Success -> _state.update { MainScreenState.Success(matchCardList = result.data) }
            }
        }
    }

    private fun fetchMatchDetails(type: String) = viewModelScope.launch(Dispatchers.IO) {
        val oldData = _state.value

        fetchMatchDetailsUseCase(type = type).collect { result ->
            when(result) {
                is RequestResult.Loading -> _state.update { MainScreenState.Loading }
                is RequestResult.Error -> _state.update {
                    Log.e("MainScreenViewModel", "fetchMatchDetails: ${result.message}")
                    MainScreenState.Error(result.message!!)
                }
                is RequestResult.Success -> _state.update {
                    try {
                        MainScreenState.Success(
                            matchCardList = (oldData as MainScreenState.Success).matchCardList,
                            details = result.data
                        )
                    } catch (e: Exception) {
                        Log.e("MainScreenViewModel", "fetchMatchDetails: ", e)
                        MainScreenState.Error(message = e.localizedMessage ?: "Unexpected error")
                    }
                }
            }
        }
    }
}