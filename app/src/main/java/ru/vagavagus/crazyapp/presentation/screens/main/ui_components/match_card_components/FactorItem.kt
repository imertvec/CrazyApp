package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.match_card_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.vagavagus.crazyapp.domain.model.Factor
import ru.vagavagus.crazyapp.ui.theme.CrazyAppTheme
import ru.vagavagus.crazyapp.ui.theme.FactorBackground
import ru.vagavagus.crazyapp.ui.theme.FactorTextColor

@Composable
fun FactorItem(
    modifier: Modifier = Modifier,
    factor: Factor,
    value: Float? = null
) {
    Column(
        modifier = modifier
            .background(FactorBackground)
            .padding(3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = factor.value.toString(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        )
        Text(
            text = "${factor.text} ${value?.toString() ?: ""}",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = FactorTextColor
            )
        )
    }
}

@Preview
@Composable
fun FactorItemPreview() {
    CrazyAppTheme {
        FactorItem(
            factor = Factor(
                value = 3.59f,
                text = "Eclair de Saa"
            ),
            value = 8.5f
        )
    }
}