package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.match_card_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import ru.vagavagus.crazyapp.common.Constants
import ru.vagavagus.crazyapp.domain.model.PorIniciar
import ru.vagavagus.crazyapp.ui.theme.CrazyAppTheme
import ru.vagavagus.crazyapp.ui.theme.PorIniciarLetterBackground
import ru.vagavagus.crazyapp.ui.theme.PorIniciarTextColor

@Composable
fun PorIniciarView(
    modifier: Modifier = Modifier,
    porIniciar: PorIniciar
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        bottomEnd = 12.dp,
                        topEnd = 2.dp,
                        bottomStart = 2.dp)
                )
                .background(PorIniciarLetterBackground)
                .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 3.dp)
        ) {
            Text(
                text = porIniciar.letters,
                fontSize = 12.sp,
                color = Color.White
            )
        }
        SubcomposeAsyncImage(
            modifier = Modifier.size(20.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data("${Constants.IMAGE_BASE_URL}/${porIniciar.icon}")
                .build(),
            contentDescription = null,
            loading = { CircularProgressIndicator() }
        )
        Text(
            text = porIniciar.text,
            style = TextStyle(
                fontSize = 12.sp,
                color = PorIniciarTextColor
            )
        )
    }
}

@Preview
@Composable
private fun PorIniciarViewPreview() {
    CrazyAppTheme {
        PorIniciarView(
            porIniciar = PorIniciar(
                letters = "A",
                icon = "",
                text = "Por iniciar"
            )
        )
    }
}