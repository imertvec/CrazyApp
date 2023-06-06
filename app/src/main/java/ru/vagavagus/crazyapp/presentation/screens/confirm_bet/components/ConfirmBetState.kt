package ru.vagavagus.crazyapp.presentation.screens.confirm_bet.components

import ru.vagavagus.crazyapp.domain.model.MatchResultTeamInfo

sealed class ConfirmBetState {
    object Loading: ConfirmBetState()
    class Error(val message: String): ConfirmBetState()
    class Success(val teamInfo: List<MatchResultTeamInfo>): ConfirmBetState()
}