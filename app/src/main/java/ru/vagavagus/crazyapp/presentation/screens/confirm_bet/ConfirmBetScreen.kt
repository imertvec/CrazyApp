package ru.vagavagus.crazyapp.presentation.screens.confirm_bet

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.R
import ru.vagavagus.crazyapp.domain.model.MatchResultTeamInfo
import ru.vagavagus.crazyapp.presentation.screens.confirm_bet.components.ConfirmBetState
import ru.vagavagus.crazyapp.presentation.screens.confirm_bet.ui_components.ResultMatchCard
import ru.vagavagus.crazyapp.ui.theme.DarkBlue
import ru.vagavagus.crazyapp.ui.theme.FactorBackground
import ru.vagavagus.crazyapp.ui.theme.Gray
import ru.vagavagus.crazyapp.ui.theme.LightGray
import ru.vagavagus.crazyapp.ui.theme.MainBackground
import ru.vagavagus.crazyapp.ui.theme.Red
import ru.vagavagus.crazyapp.ui.theme.ResultBarBackground
import ru.vagavagus.crazyapp.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmBetScreen(
    state: ConfirmBetState,
    onCloseClick: () -> Unit,
    onAllClear: () -> Unit,
    onItemDelete: (MatchResultTeamInfo) -> Unit
) {
    when(state) {
        is ConfirmBetState.Loading -> CircularProgressIndicator()
        is ConfirmBetState.Error -> Text(text = "Something went wrong: ${state.message}")
        is ConfirmBetState.Success -> {
            val teamInfo = state.teamInfo

            Scaffold(
                topBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkBlue)
                            .padding(start = 16.dp, top = 6.dp, bottom = 6.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(id = R.string.ticket),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                modifier = Modifier
                                    .clip(shape = CircleShape)
                                    .background(LightGray)
                                    .padding(start = 5.dp, end = 5.dp),
                                text = teamInfo.size.toString(),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                            Spacer(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth())
                            IconButton(onClick = onCloseClick) {
                                Icon(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(20.dp)
                                        .drawBehind {
                                            drawCircle(
                                                color = Red,
                                                radius = 40f
                                            )
                                        },
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(1.dp))
                        Spacer(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .size(25.dp, 2.dp)
                                .background(Yellow)
                        )
                    }

                },
                bottomBar = {
                    Column(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 5.dp)
                    ) {
                        //count
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(45.dp)
                                .background(ResultBarBackground)
                                .padding(horizontal = 7.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = stringResource(id = R.string.count),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = Color.Black
                                    )
                                )
                                Text(
                                    buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.Black,
                                                fontSize = 12.sp
                                            )
                                        ) { append("${stringResource(id = R.string.choose)}: ") }
                                        withStyle(
                                            style = SpanStyle(
                                                color = Red,
                                                fontSize = 13.sp
                                            )
                                        ) { append(teamInfo.size.toString()) }
                                    }
                                )
                            }
                            Spacer(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth())
                            Text(
                                modifier = Modifier
                                    .background(Color.White)
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                text = "R$ 0.00",
                                fontSize = 12.sp
                            )
                        }
                        //price
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) { append(" ${stringResource(id = R.string.price)}: ") }
                                withStyle(
                                    style = SpanStyle(
                                        color = Yellow,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) { append("0.00") }
                            }
                        )
                        Column {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(5),
                                onClick = { /*TODO*/ },
                                enabled = false,
                                colors = ButtonDefaults.buttonColors(
                                    disabledContainerColor = Gray
                                )
                            ) {
                                Text(
                                    text = stringResource(id = R.string.confirm_bet).uppercase(),
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        color = LightGray
                                    )
                                )
                            }
                            Row {
                                Button(
                                    modifier = Modifier.weight(.5f),
                                    shape = RoundedCornerShape(5),
                                    onClick = onAllClear,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Red
                                    )
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.off_bets).uppercase(),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.width(5.dp))
                                Button(
                                    modifier = Modifier.weight(.5f),
                                    shape = RoundedCornerShape(5),
                                    onClick = { /*TODO*/ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = FactorBackground
                                    )
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.split_bet).uppercase(),
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            color = Color.White
                                        )
                                    )
                                }
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
                    LazyColumn(
                        contentPadding = paddings,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(Gray)
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.BottomCenter),
                                    text = stringResource(id = R.string.search),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        color = LightGray
                                    )
                                )
                            }
                        }
                        items(teamInfo) { resultInfo ->
                            ResultMatchCard(
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                teamInfo = resultInfo,
                                onItemDelete = onItemDelete
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

            }
        }
    }
}