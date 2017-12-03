package com.jahu.playground.di

import android.content.Context
import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.memory.MockedLocalDataRepository
import com.jahu.playground.trivia.TriviaService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideTriviaService(): TriviaService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(TriviaService::class.java)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(context: Context) = SharedPreferencesManager(context)

    @Singleton
    @Provides
    fun provideLocalDataRepository(): LocalDataRepository = MockedLocalDataRepository

    @Singleton
    @Provides
    fun provideTimeProvider() = TimeProvider()
}
