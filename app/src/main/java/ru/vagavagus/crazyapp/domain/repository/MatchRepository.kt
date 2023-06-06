package ru.vagavagus.crazyapp.domain.repository

import ru.vagavagus.crazyapp.domain.model.FactorsResult
import ru.vagavagus.crazyapp.domain.model.MatchCard
import ru.vagavagus.crazyapp.domain.model.MatchDetails

interface MatchRepository {
    suspend fun fetchMatchCardList(): List<MatchCard>
    suspend fun fetchMatchDetailsList(type: String): List<MatchDetails>

    suspend fun fetchFactorsResult(matchId: Long): FactorsResult

    suspend fun fetchMatchCardById(matchCardId: Long): MatchCard
}