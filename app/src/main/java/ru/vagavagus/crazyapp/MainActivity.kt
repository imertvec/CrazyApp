package ru.vagavagus.crazyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ru.vagavagus.crazyapp.common.Constants
import ru.vagavagus.crazyapp.presentation.Screen
import ru.vagavagus.crazyapp.presentation.common.MoshiConverter
import ru.vagavagus.crazyapp.presentation.screens.confirm_bet.ConfirmBetScreen
import ru.vagavagus.crazyapp.presentation.screens.confirm_bet.components.ConfirmBetViewModel
import ru.vagavagus.crazyapp.presentation.screens.details.components.MatchDetailsScreen
import ru.vagavagus.crazyapp.presentation.screens.details.components.MatchDetailsViewModel
import ru.vagavagus.crazyapp.presentation.screens.main.MainScreen
import ru.vagavagus.crazyapp.presentation.screens.main.components.MainScreenEvent
import ru.vagavagus.crazyapp.presentation.screens.main.components.MainViewModel
import ru.vagavagus.crazyapp.ui.theme.CrazyAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrazyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val mainViewModel = hiltViewModel<MainViewModel>()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.route
                    ) {
                        composable(route = Screen.Main.route) {
                            val state = mainViewModel.state.collectAsStateWithLifecycle().value
                            MainScreen(
                                state = state,
                                bannerUrl = Constants.BANNER_URL,
                                onTabChange = { mainViewModel.onEvent(event = MainScreenEvent.FetchMatchDetails(type = it.value))},
                                onNavigate = {
                                    val converter = MoshiConverter()
                                    val json = converter.convertListObjectToJson(mainViewModel.selectedItems.toList())

                                    navController.navigate("${Screen.ConfirmBet.route}/$json")
                                },
                                onCountChange = { details, factor, isSelected, winner ->
                                    mainViewModel.onEvent(event = MainScreenEvent.ChangeSelectedItemsCount(details, factor, isSelected, winner))
                                },
                                onMatchCardClick = { cardId, count ->
                                    navController.navigate("${Screen.Details.route}/${cardId}/$count") {
                                        popUpTo(route = "${Screen.Details.route}/${cardId}/$count") {
                                            inclusive = true
                                        }
                                    }
                                },
                                selectedCount = mainViewModel.selectedItems.size
                            )
                        }
                        composable(
                            route = "${Screen.Details.route}/{matchId}/{count}",
                            arguments = listOf(
                                navArgument(name = "matchId") { type = NavType.LongType },
                                navArgument(name = "count") { type = NavType.IntType }
                            )
                        ) {
                            val count = it.arguments?.getInt("count") ?: 0
                            val viewModel = hiltViewModel<MatchDetailsViewModel>()
                            val state = viewModel.state.collectAsStateWithLifecycle().value

                            MatchDetailsScreen(
                                selectedCount = count,
                                state = state,
                                onBackArrowClick = { navController.popBackStack() }
                            )
                        }
                        composable(
                            route = "${Screen.ConfirmBet.route}/{selectedItems}",
                            arguments = listOf(
                                navArgument("selectedItems") { NavType.StringType }
                            )
                        ) {
                            val viewModel = hiltViewModel<ConfirmBetViewModel>()
                            val state = viewModel.state.collectAsStateWithLifecycle().value

                            ConfirmBetScreen(
                                state = state,
                                onCloseClick = { navController.popBackStack() },
                                onAllClear = {
                                    mainViewModel.onEvent(MainScreenEvent.ClearAllSelectedItems)
                                    navController.popBackStack()
                                },
                                onItemDelete = {
                                    mainViewModel.onEvent(MainScreenEvent.ClearSelectedItem(it))
                                    viewModel.clearSelectedItem(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}