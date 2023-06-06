package ru.vagavagus.crazyapp.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.vagavagus.crazyapp.domain.model.Championship
import ru.vagavagus.crazyapp.domain.model.Factor
import ru.vagavagus.crazyapp.domain.model.PorIniciar
import ru.vagavagus.crazyapp.domain.model.Score
import ru.vagavagus.crazyapp.domain.model.Team

@Keep
@JsonClass(generateAdapter = true)
data class MatchCardDto(
    @Json(name = "id") val id: Long,
    @Json(name = "championship") val championship: Championship,
    @Json(name = "team1") val team1: Team,
    @Json(name = "team2") val team2: Team,
    @Json(name = "total_goals") val totalGoals: Int,
    @Json(name = "mais_de") val maisDe: Factor,
    @Json(name = "empate") val empate: Factor,
    @Json(name = "eclair_de_saa") val eclairDeSaa: Factor,
    @Json(name = "por_iniciar") val porIniciar: PorIniciar,
    @Json(name = "score") val score: Score,
)