package ru.vagavagus.crazyapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/9/matchcards_list.json")
    suspend fun fetchMatchCards(): List<MatchCardDto>

    @GET("/9/matchdetails_{sport}.json")
    suspend fun fetchMatchDetails(
        @Path(value = "sport") sport: String
    ): List<MatchDetailsDto>

    @GET("/9/confirmbet_entities.json")
    suspend fun fetchFactors(): List<FactorsResultDto>
}