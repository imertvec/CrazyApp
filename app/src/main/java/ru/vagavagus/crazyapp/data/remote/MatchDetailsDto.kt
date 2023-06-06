package ru.vagavagus.crazyapp.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.vagavagus.crazyapp.domain.model.FactorGroup
import ru.vagavagus.crazyapp.domain.model.Team

@Keep
@JsonClass(generateAdapter = true)
data class MatchDetailsDto(
    @Json(name = "id") val id: Long,
    @Json(name = "team1") val team1: Team,
    @Json(name = "team2") val team2: Team,
    @Json(name = "datetime") val datetime: Long,
    @Json(name = "bonus") val bonus: Int,
    @Json(name = "factors") val factors: FactorGroup
)