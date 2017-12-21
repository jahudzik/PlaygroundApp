package com.jahu.playground.di

import android.content.Context
import com.jahu.playground.features.game.random.RandomNumberGenerator
import com.jahu.playground.features.game.random.RandomSequenceGenerator
import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.repositories.DataSource
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.memory.InMemoryDataSource
import com.jahu.playground.trivia.TriviaService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@SuppressWarnings("FunctionMaxLength")
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
    fun provideLocalDataRepository(): DataSource = InMemoryDataSource()

    @Singleton
    @Provides
    fun provideTimeProvider() = TimeProvider()

    @Singleton
    @Provides
    fun provideRandomNumberGenerator() = RandomNumberGenerator()

    @Singleton
    @Provides
    fun provideSequenceGenerator(numberGenerator: RandomNumberGenerator) = RandomSequenceGenerator(numberGenerator)

}
