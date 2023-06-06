package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.factor_row

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.ui.theme.FactorBackground
import ru.vagavagus.crazyapp.ui.theme.Yellow

@Composable
fun FactorValueBox(
    modifier: Modifier = Modifier,
    value: Float,
    selected: Boolean,
    onBoxClick: () -> Unit,
    textSize: TextUnit = 12.sp
) {
    val background = if(selected) Yellow else FactorBackground
    val textColor = if(selected) Color.Black else Color.White

    Box(
        modifier = modifier
            .background(background)
            .clickable { onBoxClick() }
            .padding(start = 25.dp, end = 25.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = value.toString(),
            style = TextStyle(
                fontSize = textSize,
                color = textColor
            )
        )
    }
}