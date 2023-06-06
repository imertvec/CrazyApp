package ru.vagavagus.crazyapp.presentation.screens.main.components

import ru.vagavagus.crazyapp.domain.model.MatchDetails
import ru.vagavagus.crazyapp.domain.model.MatchResultTeamInfo
import ru.vagavagus.crazyapp.domain.model.Team

sealed class MainScreenEvent {
    object FetchMatchCardList: MainScreenEvent()
    class FetchMatchDetails(val type: String): MainScreenEvent()
    class ChangeSelectedItemsCount(
        val details: MatchDetails,
        val factor: Float,
        val isSelected: Boolean,
        val winner: Team? = null
    ): MainScreenEvent()
    object  ClearAllSelectedItems: MainScreenEvent()
    class  ClearSelectedItem(val item: MatchResultTeamInfo): MainScreenEvent()
}