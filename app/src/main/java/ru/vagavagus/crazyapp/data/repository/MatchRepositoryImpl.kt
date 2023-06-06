package ru.vagavagus.crazyapp.data.repository

import ru.vagavagus.crazyapp.data.remote.Api
import ru.vagavagus.crazyapp.data.remote.FactorsResultDto
import ru.vagavagus.crazyapp.data.remote.MatchCardDto
import ru.vagavagus.crazyapp.data.remote.MatchDetailsDto
import ru.vagavagus.crazyapp.domain.model.FactorsResult
import ru.vagavagus.crazyapp.domain.model.MatchCard
import ru.vagavagus.crazyapp.domain.model.MatchDetails
import ru.vagavagus.crazyapp.domain.repository.MatchRepository

class MatchRepositoryImpl(
    private val api: Api
): MatchRepository {
    override suspend fun fetchMatchCardList(): List<MatchCard> {
        return api.fetchMatchCards().map { it.toMatchCard() }
    }

    override suspend fun fetchMatchDetailsList(type: String): List<MatchDetails> {
        return api.fetchMatchDetails(type).map { it.toMatchDetails() }
    }

    override suspend fun fetchFactorsResult(matchId: Long): FactorsResult {
        return api.fetchFactors().first { it.matchId == matchId }.toFactorsResult()
    }

    override suspend fun fetchMatchCardById(matchCardId: Long): MatchCard {
        return api.fetchMatchCards().first { it.id == matchCardId }.toMatchCard()
    }

    private fun MatchCardDto.toMatchCard() = MatchCard(
        id = id,
        championship = championship,
        team1 = team1,
        team2 = team2,
        totalGoals = totalGoals,
        maisDe = maisDe,
        empate = empate,
        eclairDeSaa = eclairDeSaa,
        porIniciar = porIniciar,
        score = score
    )

    private fun MatchDetailsDto.toMatchDetails() = MatchDetails(
        id = id,
        team1 = team1,
        team2 = team2,
        datetime = datetime,
        bonus = bonus,
        factors = factors
    )

    private fun FactorsResultDto.toFactorsResult() = FactorsResult(
        matchId = matchId,
        factorLeft = factorLeft,
        factorRight = factorRight,
        defaultFactors = defaultFactors
    )
}