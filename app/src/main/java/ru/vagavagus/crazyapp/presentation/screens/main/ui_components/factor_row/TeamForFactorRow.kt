package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.factor_row

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.domain.model.MatchDetails
import ru.vagavagus.crazyapp.presentation.screens.main.ui_components.match_card_components.TeamView
import ru.vagavagus.crazyapp.ui.theme.FactorBackground
import ru.vagavagus.crazyapp.ui.theme.FactorTextColor
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun TeamForFactorRow(
    modifier: Modifier = Modifier,
    details: MatchDetails,
    textSize: TextUnit = 14.sp
) {
    val sdf = SimpleDateFormat("dd/MM HH:mm")

    Column(
        modifier = modifier
    ) {
        TeamView(
            team = details.team1,
            fontSize = 12.sp,
            imageSize = 20.dp
        )
        TeamView(
            team = details.team2,
            fontSize = 12.sp,
            imageSize = 20.dp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = sdf.format(details.datetime),
                style = TextStyle(
                    color = FactorTextColor,
                    fontSize = textSize
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(30))
                    .background(FactorBackground)
                    .padding(start = 3.dp, end = 3.dp)
                ,
                text = "+${details.bonus}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = textSize
                )
            )
        }
    }
}
