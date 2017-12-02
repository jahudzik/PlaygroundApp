package com.jahu.playground.di

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

}
