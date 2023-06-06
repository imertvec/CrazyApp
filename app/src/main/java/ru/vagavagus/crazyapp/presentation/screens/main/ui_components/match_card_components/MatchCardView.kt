package ru.vagavagus.crazyapp.presentation.screens.main.ui_components.match_card_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import ru.vagavagus.crazyapp.R
import ru.vagavagus.crazyapp.common.Constants
import ru.vagavagus.crazyapp.domain.model.Championship
import ru.vagavagus.crazyapp.domain.model.Factor
import ru.vagavagus.crazyapp.domain.model.MatchCard
import ru.vagavagus.crazyapp.domain.model.PorIniciar
import ru.vagavagus.crazyapp.domain.model.Team
import ru.vagavagus.crazyapp.ui.theme.ChampionshipTextColor
import ru.vagavagus.crazyapp.ui.theme.CrazyAppTheme
import ru.vagavagus.crazyapp.ui.theme.MatchCardBackground
import ru.vagavagus.crazyapp.ui.theme.TotalGoalsTextColor

@Composable
fun MatchCardView(
    modifier: Modifier = Modifier,
    matchCard: MatchCard,
    onItemClick: (MatchCard) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(MatchCardBackground)
            .clickable { onItemClick(matchCard) }
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 8.dp)
    ) {
        //championship
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(20.dp)
                    .clip(shape = CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${Constants.COUNTRY_IMAGE_BASE_URL}/${matchCard.championship.image}")
                    .build(),
                contentDescription = null,
                loading = { CircularProgressIndicator() }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "${matchCard.championship.country} - ${matchCard.championship.name}",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = ChampionshipTextColor
                )
            )
            Spacer(modifier = Modifier.weight(1f).fillMaxWidth())
            PorIniciarView(
                porIniciar = matchCard.porIniciar
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        //teams
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            TeamView(team = matchCard.team1)
            TeamView(team = matchCard.team2)
        }
        //total goals
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            text = stringResource(id = R.string.total_goals),
            style = TextStyle(
                fontSize = 12.sp,
                color = TotalGoalsTextColor
            )
        )
        //factors
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FactorItem(
                modifier = Modifier.weight(.3f),
                factor = matchCard.maisDe,
                value = 8.5f /*TODO: take value from server*/
            )
            FactorItem(
                modifier = Modifier.weight(.3f),
                factor = matchCard.empate
            )
            FactorItem(
                modifier = Modifier.weight(.3f),
                factor = matchCard.eclairDeSaa
            )
        }
    }
}

@Preview
@Composable
fun MatchCardViewPreview() {
    CrazyAppTheme {
        MatchCardView(
            matchCard = MatchCard(
                championship = Championship(
                    country = "China",
                    name = "Super Liga Chinesa 2023",
                    image = ""
                ),
                team1 = Team("Beijing Guo. FC", ""),
                team2 = Team("Cangzhou Mig. Lio.", ""),
                totalGoals = 0,
                maisDe = Factor(4.93f, text = "Mais de"),
                empate = Factor(3.35f, text = "Empate"),
                eclairDeSaa = Factor(3.59f, "Eclair de Saa"),
                porIniciar = PorIniciar(
                    letters = "A",
                    icon = "",
                    text = "Por iniciar"
                )
            ),
            onItemClick = {}
        )
    }
}