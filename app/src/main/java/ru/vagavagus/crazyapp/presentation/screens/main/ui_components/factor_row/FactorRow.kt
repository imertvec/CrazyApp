package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.factor_row

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.domain.model.MatchDetails
import ru.vagavagus.crazyapp.domain.model.Team

@Composable
fun FactorRow(
    modifier: Modifier = Modifier,
    details: MatchDetails,
    onFactorCellClick: (details: MatchDetails, factor: Float, isSelected: Boolean, winner: Team?) -> Unit
) {
    var firstSelected by remember { mutableStateOf(false) }
    var tieSelected by remember { mutableStateOf(false) }
    var secondSelected by remember { mutableStateOf(false) }

    Column {
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Gray)
        )
        Row(
            modifier = modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TeamForFactorRow(details = details, textSize = 12.sp)
            Spacer(modifier = Modifier
                .weight(1f)
                .fillMaxWidth())
            FactorValueBox(
                value = details.factors.first,
                selected = firstSelected,
                onBoxClick = {
                    firstSelected = !firstSelected
                    onFactorCellClick(details, details.factors.first, firstSelected, details.team1)
                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            FactorValueBox(
                value = details.factors.tie,
                selected = tieSelected,
                onBoxClick = {
                    tieSelected = !tieSelected
                    onFactorCellClick(details, details.factors.tie, tieSelected, null)
                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            FactorValueBox(
                value = details.factors.second,
                selected = secondSelected,
                onBoxClick = {
                    secondSelected = !secondSelected
                    onFactorCellClick(details, details.factors.second, secondSelected, details.team2)
                }
            )
        }
    }
}