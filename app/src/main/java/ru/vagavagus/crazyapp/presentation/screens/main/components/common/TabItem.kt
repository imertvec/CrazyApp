package ru.vagavagus.crazyapp.presentation.screens.main.components.common

import ru.vagavagus.crazyapp.R

sealed class TabItem(val name: String, val icon: Int, val value: String) {
    object Football: TabItem(name = "Futebol", icon = R.drawable.football_icon, value = "football")
    object CyberFootball: TabItem(name = "eFutebol", icon = R.drawable.cyber_football_icon, value = "cybersport")
    object Basketball: TabItem(name = "Basequte", icon = R.drawable.basketball_icon, value = "basketball")
    object Tennis: TabItem(name = "Tenis", icon = R.drawable.tennis_icon, value = "tennis")
}