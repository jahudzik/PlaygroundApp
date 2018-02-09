package com.jahu.playground.di

import android.content.Context
import com.jahu.playground.BuildConfig
import com.jahu.playground.IdlingResources
import com.jahu.playground.data.DataSource
import com.jahu.playground.data.SharedPreferencesManager
import com.jahu.playground.data.memory.InMemoryDataSource
import com.jahu.playground.features.game.random.RandomNumberGenerator
import com.jahu.playground.features.game.random.RandomSequenceGenerator
import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.trivia.TriviaService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@SuppressWarnings("FunctionMaxLength")
class DataModule {

    @Singleton
    @Provides
    fun provideTriviaService(): TriviaService {
        val okHttpClient = OkHttpClient()
        if (BuildConfig.DEBUG) {
            IdlingResources.registerOkHttp(okHttpClient)
        }

        val retrofit = Retrofit.Builder()
                .baseUrl("https://opentdb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
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
