package ru.vagavagus.crazyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchCard(
    val id: Long = 0,
    val championship: Championship,
    val team1: Team,
    val team2: Team,
    val totalGoals: Int,
    val maisDe: Factor,
    val empate: Factor,
    val eclairDeSaa: Factor,
    val porIniciar: PorIniciar,
    val score: Score? = null
)

@JsonClass(generateAdapter = true)
data class Team(
    val name: String,
    val image: String
)

@JsonClass(generateAdapter = true)
data class Championship(
    @Json(name = "country") val country: String,
    @Json(name = "name") val name: String,
    @Json(name = "image") val image: String
)

@JsonClass(generateAdapter = true)
data class Factor(
    @Json(name = "value") val value: Float,
    @Json(name = "text") val text: String
)

@JsonClass(generateAdapter = true)
data class PorIniciar(
    @Json(name = "letters") val letters: String,
    @Json(name = "icon") val icon: String,
    @Json(name = "text") val text: String
)
@JsonClass(generateAdapter = true)
data class Score(
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "score1") val score1: Int,
    @Json(name = "score2") val score2: Int
)