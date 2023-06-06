package ru.vagavagus.crazyapp.presentation.screens.confirm_bet.components

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ru.vagavagus.crazyapp.domain.model.MatchResultTeamInfo
import ru.vagavagus.crazyapp.presentation.common.MoshiConverter
import javax.inject.Inject

@HiltViewModel
class ConfirmBetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {


    private val converter = MoshiConverter()
    private val json = savedStateHandle.get<String>("selectedItems")
    private val selectedItems = converter.convertJsonToListObject<MatchResultTeamInfo>(json!!).toMutableList()

    private val _state: MutableStateFlow<ConfirmBetState> = MutableStateFlow(ConfirmBetState.Loading)
    val state: StateFlow<ConfirmBetState> = _state

    init {
        _state.update { ConfirmBetState.Success(selectedItems) }
    }

    fun clearSelectedItem(item: MatchResultTeamInfo) {
        selectedItems.remove(item)
        _state.update { ConfirmBetState.Success(selectedItems) }
    }
}