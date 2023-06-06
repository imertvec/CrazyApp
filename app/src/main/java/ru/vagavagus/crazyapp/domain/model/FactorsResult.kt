package ru.vagavagus.crazyapp.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FactorsResult(
    val matchId: Long,
    val factorLeft: List<Factor>,
    val factorRight: List<Factor>,
    val defaultFactors: FactorGroup
)