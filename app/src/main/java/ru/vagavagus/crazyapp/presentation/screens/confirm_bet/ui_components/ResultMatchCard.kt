package ru.vagavagus.crazyapp.presentation.screens.confirm_bet.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.R
import ru.vagavagus.crazyapp.domain.model.MatchResultTeamInfo
import ru.vagavagus.crazyapp.domain.model.Team
import ru.vagavagus.crazyapp.ui.theme.CrazyAppTheme
import ru.vagavagus.crazyapp.ui.theme.DoneGray
import ru.vagavagus.crazyapp.ui.theme.FactorBackground
import ru.vagavagus.crazyapp.ui.theme.GrayResultTextColor
import ru.vagavagus.crazyapp.ui.theme.Yellow

@Composable
fun ResultMatchCard(
    modifier: Modifier = Modifier,
    teamInfo: MatchResultTeamInfo,
    onItemDelete: (MatchResultTeamInfo) -> Unit
) {
    Column(
        modifier = modifier
            .background(FactorBackground)
            .padding(4.dp)
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(DoneGray),
                imageVector = Icons.Default.Done,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(2.dp))
            Column {
                Row {
                    Text(
                        text = "${teamInfo.team1.name} - ${teamInfo.team2.name}",
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
                            .size(20.dp)
                            .clickable { onItemDelete(teamInfo) },
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Text(
                    text = "${stringResource(id = R.string.result)}:",
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = GrayResultTextColor
                    )
                )
                Row(
                    modifier = Modifier.padding(end = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = teamInfo.winner?.name ?: stringResource(id = R.string.empate),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth())
                    Text(
                        text = teamInfo.factor.toString(),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Yellow
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ResultMatchCardPreview() {
    CrazyAppTheme {
        ResultMatchCard(
            teamInfo = MatchResultTeamInfo(
                detailsId = 1,
                team1 = Team("FC Urartu 2", ""),
                team2 = Team("Shirak FC II", ""),
                winner = Team("FC Urartu 2", ""),
                factor = 1.86f
            ),
            onItemDelete = {}
        )
    }
}