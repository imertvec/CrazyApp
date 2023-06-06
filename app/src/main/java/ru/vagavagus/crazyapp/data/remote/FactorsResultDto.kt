package ru.vagavagus.crazyapp.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.vagavagus.crazyapp.domain.model.Factor
import ru.vagavagus.crazyapp.domain.model.FactorGroup

@Keep
@JsonClass(generateAdapter = true)
data class FactorsResultDto(
    @Json(name = "id") val matchId: Long,
    @Json(name = "factor_left") val factorLeft: List<Factor>,
    @Json(name = "factor_right") val factorRight: List<Factor>,
    @Json(name = "default_factors") val defaultFactors: FactorGroup
)