package ru.vagavagus.crazyapp.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import ru.vagavagus.crazyapp.R
import ru.vagavagus.crazyapp.domain.model.MatchDetails
import ru.vagavagus.crazyapp.domain.model.Team
import ru.vagavagus.crazyapp.presentation.screens.main.components.MainScreenState
import ru.vagavagus.crazyapp.presentation.screens.main.components.common.TabItem
import ru.vagavagus.crazyapp.presentation.screens.main.ui_components.factor_row.FactorRow
import ru.vagavagus.crazyapp.presentation.screens.main.ui_components.match_card_components.MatchCardView
import ru.vagavagus.crazyapp.presentation.screens.main.ui_components.sport_tab_components.TabBar
import ru.vagavagus.crazyapp.ui.theme.FactorBackground
import ru.vagavagus.crazyapp.ui.theme.MainBackground
import ru.vagavagus.crazyapp.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: MainScreenState,
    onTabChange: (TabItem) -> Unit,
    onNavigate: () -> Unit,
    onCountChange: (details: MatchDetails, factor: Float, isSelected: Boolean, winner: Team?) -> Unit,
    onMatchCardClick: (Long, Int) -> Unit,
    bannerUrl: String,
    selectedCount: Int
) {
    val selectedSport: MutableState<TabItem> = remember { mutableStateOf(TabItem.Football) }

    Scaffold(
        bottomBar = {
            if(selectedCount > 0) {
                Box(
                    modifier = Modifier
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .height(50.dp)
                            .fillMaxWidth()
                            .background(FactorBackground)
                    )
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.Center)
                            .clip(shape = CircleShape)
                            .background(Yellow)
                            .clickable { onNavigate() }
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = selectedCount.toString(),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                    }
                }
            }
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MainBackground)
        ) {
            when(state) {
                is MainScreenState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
                is MainScreenState.Error -> Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = state.message
                )
                is MainScreenState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 5.dp, end = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = paddings
                    ) {
                        //banner
                        item {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, bottom = 10.dp),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(bannerUrl)
                                    .build(),
                                contentDescription = null,
                                loading = { CircularProgressIndicator() },
                                contentScale = ContentScale.FillWidth,
                                error = {
                                    println("message -> ${it.result.throwable}")
                                }
                            )
                        }
                        //match cards
                        if(state.matchCardList != null) {
                            item {
                                LazyRow(
                                    modifier = Modifier.fillParentMaxWidth()
                                ) {
                                    items(state.matchCardList) { card ->
                                        MatchCardView(
                                            modifier = Modifier.fillParentMaxWidth(),
                                            matchCard = card,
                                            onItemClick = { onMatchCardClick(card.id, selectedCount) }
                                        )
                                    }
                                }
                            }
                        } else {
                            item { CircularProgressIndicator() }
                        }
                        //popular
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(
                                        top = 30.dp,
                                        bottom = 5.dp,
                                        start = 10.dp,
                                        end = 10.dp,
                                    )
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .background(FactorBackground)
                                    .padding(start = 10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.CenterStart),
                                    text = stringResource(id = R.string.popular),
                                    style = TextStyle(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Icon(
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        //tab bar
                        item {
                            TabBar(
                                selectedTab = selectedSport.value,
                                onTabChange = {
                                    selectedSport.value = it
                                    onTabChange(it)
                                }
                            )
                        }
                        //matches
                        if(state.details != null) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 33.dp),
                                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.End)
                                ) {
                                    val textStyle = TextStyle(
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )

                                    Text(
                                        text = "1",
                                        style = textStyle
                                    )
                                    Spacer(modifier = Modifier.width(60.dp))
                                    Text(
                                        text = "X",
                                        style = textStyle
                                    )
                                    Spacer(modifier = Modifier.width(60.dp))
                                    Text(
                                        text = "2",
                                        style = textStyle
                                    )
                                }
                            }
                            items(state.details) { detail ->
                                FactorRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    details = detail,
                                    onFactorCellClick = onCountChange
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
