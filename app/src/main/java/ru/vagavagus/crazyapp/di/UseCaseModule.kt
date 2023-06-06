package ru.vagavagus.crazyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vagavagus.crazyapp.domain.repository.MatchRepository
import ru.vagavagus.crazyapp.domain.use_cases.FetchFactorsResultUseCase
import ru.vagavagus.crazyapp.domain.use_cases.FetchMatchCardByIdUseCase
import ru.vagavagus.crazyapp.domain.use_cases.FetchMatchCardListUseCase
import ru.vagavagus.crazyapp.domain.use_cases.FetchMatchDetailsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchFactorsResultUseCase(repository: MatchRepository): FetchFactorsResultUseCase {
        return FetchFactorsResultUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchMatchCardByIdUseCase(repository: MatchRepository): FetchMatchCardByIdUseCase {
        return FetchMatchCardByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchMatchCardListUseCase(repository: MatchRepository): FetchMatchCardListUseCase {
        return FetchMatchCardListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchMatchDetailsUseCase(repository: MatchRepository): FetchMatchDetailsUseCase {
        return FetchMatchDetailsUseCase(repository)
    }
}