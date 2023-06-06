package ru.vagavagus.crazyapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.vagavagus.crazyapp.common.Constants
import ru.vagavagus.crazyapp.data.remote.Api
import ru.vagavagus.crazyapp.data.repository.MatchRepositoryImpl
import ru.vagavagus.crazyapp.domain.repository.MatchRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideMatchRepository(api: Api): MatchRepository {
        return MatchRepositoryImpl(api)
    }
}