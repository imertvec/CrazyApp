package ru.vagavagus.crazyapp.domain.use_cases

import android.util.Log
import kotlinx.coroutines.flow.flow
import ru.vagavagus.crazyapp.common.RequestResult
import ru.vagavagus.crazyapp.domain.repository.MatchRepository

class FetchMatchDetailsUseCase (
    private val repository: MatchRepository
) {
    suspend operator fun invoke(type: String) = flow {
        try {
            emit(RequestResult.Loading())
            val data = repository.fetchMatchDetailsList(type)
            emit(RequestResult.Success(data))
        } catch (e: Exception) {
            Log.e("MatchDetailsUseCase", "invoke: ", e)
            emit(RequestResult.Error(message = e.localizedMessage ?: "Error loading"))
        }
    }
}