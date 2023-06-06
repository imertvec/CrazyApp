package ru.vagavagus.crazyapp.presentation.screens.details.components

import androidx.lifecycle.SavedStateHandle
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
import ru.vagavagus.crazyapp.domain.use_cases.FetchFactorsResultUseCase
import ru.vagavagus.crazyapp.domain.use_cases.FetchMatchCardByIdUseCase
import ru.vagavagus.crazyapp.presentation.screens.details.MatchDetailsScreenState
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    private val fetchFactorsResultUseCase: FetchFactorsResultUseCase,
    private val fetchMatchCardByIdUseCase: FetchMatchCardByIdUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val matchId = savedStateHandle.get<Long>("matchId") ?: 1

    private val _state: MutableStateFlow<MatchDetailsScreenState> = MutableStateFlow(
        MatchDetailsScreenState.Loading
    )
    val state: StateFlow<MatchDetailsScreenState> = _state

    init {
        fetchFullMatchInfo(matchId)
    }

    private fun fetchFullMatchInfo(matchId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchFactorsResult(matchId)
            delay(1000)
            fetchMatchCardById(matchId)
        }
    }

    private fun fetchFactorsResult(matchId: Long) = viewModelScope.launch(Dispatchers.IO) {
        fetchFactorsResultUseCase(matchId).collect { result ->
            when(result) {
                is RequestResult.Loading -> _state.update { MatchDetailsScreenState.Loading }
                is RequestResult.Error -> _state.update {
                    MatchDetailsScreenState.Error(
                        message = result.message ?: "Unexpected error"
                    )
                }
                is RequestResult.Success -> _state.update { MatchDetailsScreenState.Success(details = result.data!!) }
            }
        }
    }

    private fun fetchMatchCardById(matchCardId: Long) = viewModelScope.launch(Dispatchers.IO) {
        val oldData = _state.value

        fetchMatchCardByIdUseCase(matchCardId).collect { result ->
            when(result) {
                is RequestResult.Loading -> _state.update { MatchDetailsScreenState.Loading }
                is RequestResult.Error -> _state.update {
                    println(result.message)
                    MatchDetailsScreenState.Error(message = result.message ?: "Unexpected error")
                }
                is RequestResult.Success -> _state.update {

                    try {
                        MatchDetailsScreenState.Success(
                            details = (oldData as MatchDetailsScreenState.Success).details,
                            matchCard = result.data
                        )
                    } catch (e: Exception) {
                        println(e.localizedMessage)
                        MatchDetailsScreenState.Error(
                            message = e.localizedMessage ?: "Unexpected error"
                        )
                    }
                }
            }
        }

    }
}