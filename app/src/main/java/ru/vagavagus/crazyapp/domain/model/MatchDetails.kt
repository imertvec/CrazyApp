package ru.vagavagus.crazyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchDetails(
    val id: Long,
    val team1: Team,
    val team2: Team,
    val datetime: Long,
    val bonus: Int,
    val factors: FactorGroup
)
@JsonClass(generateAdapter = true)
data class FactorGroup(
    @Json(name = "first") val first: Float,
    @Json(name = "tie") val tie: Float,
    @Json(name = "block") val block: String? = null,
    @Json(name = "second") val second: Float
)