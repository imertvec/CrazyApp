package ru.vagavagus.crazyapp.domain.use_cases

import kotlinx.coroutines.flow.channelFlow
import ru.vagavagus.crazyapp.common.RequestResult
import ru.vagavagus.crazyapp.domain.repository.MatchRepository

class FetchMatchCardByIdUseCase(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(matchCardId: Long) = channelFlow {
        try {
            send(RequestResult.Loading())
            val data = repository.fetchMatchCardById(matchCardId)
            send(RequestResult.Success(data))
        } catch (e: Exception) {
            send(RequestResult.Error(message = e.localizedMessage ?: "Error loading"))
        }
    }
}