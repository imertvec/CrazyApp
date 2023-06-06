package ru.vagavagus.crazyapp.domain.use_cases

import kotlinx.coroutines.flow.flow
import ru.vagavagus.crazyapp.common.RequestResult
import ru.vagavagus.crazyapp.domain.repository.MatchRepository

class FetchFactorsResultUseCase(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(matchId: Long) = flow {
        try {
            emit(RequestResult.Loading())
            val data = repository.fetchFactorsResult(matchId)
            emit(RequestResult.Success(data = data))
        } catch (e: Exception) {
            emit(RequestResult.Error(message = e.localizedMessage ?: "Error loading"))
        }
    }
}