package ru.vagavagus.crazyapp.presentation.screens.details

import ru.vagavagus.crazyapp.domain.model.FactorsResult
import ru.vagavagus.crazyapp.domain.model.MatchCard

sealed class MatchDetailsScreenState {
    object Loading: MatchDetailsScreenState()
    class Error(val message: String): MatchDetailsScreenState()
    class Success(val details: FactorsResult, val matchCard: MatchCard? = null): MatchDetailsScreenState()
}