package ru.vagavagus.crazyapp.presentation.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.R
import ru.vagavagus.crazyapp.domain.model.Championship
import ru.vagavagus.crazyapp.domain.model.Factor
import ru.vagavagus.crazyapp.domain.model.MatchCard
import ru.vagavagus.crazyapp.domain.model.PorIniciar
import ru.vagavagus.crazyapp.domain.model.Score
import ru.vagavagus.crazyapp.domain.model.Team
import ru.vagavagus.crazyapp.presentation.screens.main.ui_components.match_card_components.FactorItem
import ru.vagavagus.crazyapp.ui.theme.FactorBackground
import ru.vagavagus.crazyapp.ui.theme.MainBackground
import ru.vagavagus.crazyapp.ui.theme.MatchCardBackground
import ru.vagavagus.crazyapp.ui.theme.Orange
import ru.vagavagus.crazyapp.ui.theme.TimestampColor
import ru.vagavagus.crazyapp.ui.theme.Yellow
import java.text.SimpleDateFormat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailsScreen(
    matchCard: MatchCard,
    selectedCount: Int
) {
    val sdf = SimpleDateFormat("MM:ss")

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FactorBackground)
                    .padding(start = 5.dp, end = 3.dp, top = 7.dp, bottom = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    text = "● ${matchCard.championship.name} ● ${matchCard.team1.name} - ${matchCard.team2.name}",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth())
                Icon(
                    modifier = Modifier
                        .border(0.5f.dp, Color.Gray, shape = RoundedCornerShape(30))
                        .padding(1.dp),
                    imageVector = Icons.Default.KeyboardDoubleArrowUp,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier.background(MainBackground)
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
                        .clip(shape = CircleShape)
                        .align(Alignment.Center)
                        .background(Yellow)
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
    ) { paddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings)
                .background(MainBackground)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MatchCardBackground)
                    .padding(start = 8.dp, end = 8.dp, top = 5.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                matchCard.score?.let { score ->
                    //team1
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.tshirt_icon),
                            contentDescription = null,
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "MBA",
                            color = Color.White
                        )
                    }
                    //score
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .border(
                                    1.dp,
                                    Color.Gray,
                                    RoundedCornerShape(
                                        bottomStartPercent = 30,
                                        bottomEndPercent = 30
                                    )
                                )
                                .clip(
                                    shape = RoundedCornerShape(
                                        bottomStartPercent = 30,
                                        bottomEndPercent = 30
                                    )
                                )
                                .background(TimestampColor)
                                .padding(start = 3.dp, end = 3.dp),
                            text = sdf.format(score.timestamp),
                            color = Color.White,
                            fontSize = 10.sp
                        )
                        Text(
                            text = "${score.score1} : ${score.score2}",
                            color = Color.White
                        )
                    }
                    //team2
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PFK",
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(R.drawable.tshirt_icon),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //total goals
                    Row(
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .background(FactorBackground)
                            .padding(start = 10.dp, end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.total_goals),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = stringResource(id = R.string.close_bet),
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Orange
                            )
                        )
                        Spacer(modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth())
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.PushPin,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    //factors
                    val commonPaddings = 4.dp

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MatchCardBackground)
                            .padding(commonPaddings)
                    ) {
                        Column(
                            modifier = Modifier.weight(.5f)
                        ) {
                            FactorItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(commonPaddings),
                                factor = matchCard.maisDe,
                                value = 8.4f
                            )
                            FactorItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(commonPaddings),
                                factor = matchCard.maisDe,
                                value = 8.4f
                            )
                            FactorItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(commonPaddings),
                                factor = matchCard.maisDe,
                                value = 8.4f
                            )
                        }
                        Column(
                            modifier = Modifier.weight(.5f)
                        ) {
                            FactorItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(commonPaddings),
                                factor = matchCard.maisDe,
                                value = 8.4f
                            )
                            FactorItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(commonPaddings),
                                factor = matchCard.maisDe,
                                value = 8.4f
                            )
                            FactorItem(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(commonPaddings),
                                factor = matchCard.maisDe,
                                value = 8.4f
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun MatchDetailsScreenPreview() {
    MatchDetailsScreen(
        matchCard = MatchCard(
            championship = Championship(
                country = "China",
                name = "Ao Vivo",
                image = ""
            ),
            team1 = Team("Moik Baku",""),
            team2 = Team("Pfk Neftci Bak.", ""),
            totalGoals = 0,
            maisDe = Factor(4.93f, text = "Mais de"),
            empate = Factor(3.35f, text = "Empate"),
            eclairDeSaa = Factor(3.59f, "Eclair de Saa"),
            porIniciar = PorIniciar(
                letters = "A",
                icon = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/YouTube_full-color_icon_%282017%29.svg/1024px-YouTube_full-color_icon_%282017%29.svg.png",
                text = "Por iniciar"
            ),
            score = Score(
                timestamp = System.currentTimeMillis(),
                score1 = 2,
                score2 = 3
            )
        ),
        selectedCount = 3
    )
}