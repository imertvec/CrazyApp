package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.match_card_components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import ru.vagavagus.crazyapp.common.Constants
import ru.vagavagus.crazyapp.domain.model.Team

@Composable
fun TeamView(
    team: Team,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    imageSize: Dp = 30.dp
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.size(imageSize),
            model = ImageRequest.Builder(LocalContext.current)
                .data("${Constants.TEAM_IMAGE_BASE_URL}/${team.image}")
                .build(),
            contentDescription = null,
            loading = { CircularProgressIndicator() }
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = team.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                color = Color.White
            )
        )
    }
}

@Preview
@Composable
private fun TeamViewPreview() {
    TeamView(team = Team("Beijing Guo. FC", ""))
}