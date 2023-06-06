package ru.vagavagus.crazyapp.presentation.screens.main.components

import ru.vagavagus.crazyapp.domain.model.MatchCard
import ru.vagavagus.crazyapp.domain.model.MatchDetails


sealed class MainScreenState {
    class Error(val message: String): MainScreenState()
    class Success(val matchCardList: List<MatchCard>? = null, val details: List<MatchDetails>? = null): MainScreenState()
    object Loading: MainScreenState()
}
