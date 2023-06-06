package ru.vagavagus.crazyapp.presentation

sealed class Screen(val route: String) {
    object Main: Screen(route = "main_screen")
    object ConfirmBet: Screen(route = "confirm_bet_screen")
    object Details: Screen(route = "details_screen")
}