package ru.vagavagus.crazyapp.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchResultTeamInfo(
    val detailsId: Long,
    val team1: Team,
    val team2: Team,
    val winner: Team? = null,
    val factor: Float
)