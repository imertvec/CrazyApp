package ru.vagavagus.crazyapp.domain.use_cases

import kotlinx.coroutines.flow.flow
import ru.vagavagus.crazyapp.common.RequestResult
import ru.vagavagus.crazyapp.domain.repository.MatchRepository

class FetchMatchCardListUseCase(
    private val repository: MatchRepository
) {
    suspend operator fun invoke() = flow {
        try {
            emit(RequestResult.Loading())
            val data = repository.fetchMatchCardList()
            emit(RequestResult.Success(data))
        } catch (e: Exception) {
            emit(RequestResult.Error(message = e.localizedMessage ?: "Error loading"))
        }
    }
}