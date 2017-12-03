package com.jahu.playground.di

import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.trivia.TriviaService
import com.jahu.playground.usecases.games.AddGameResultUseCase
import com.jahu.playground.usecases.games.GetLeaderboardEntriesUseCase
import com.jahu.playground.usecases.games.GetNewQuestionsUseCase
import com.jahu.playground.usecases.users.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@SuppressWarnings("FunctionMaxLength")
class UseCaseModule {

    @Singleton
    @Provides
    fun provideAddUserUseCase(localDataRepository: LocalDataRepository)
            = AddUserUseCase(localDataRepository)

    @Singleton
    @Provides
    fun provideGetActualUserUseCase(localDataRepository: LocalDataRepository,
                                    sharedPreferencesManager: SharedPreferencesManager)
            = GetActualUserUseCase(sharedPreferencesManager, localDataRepository)

    @Singleton
    @Provides
    fun provideGetUsersUseCase(localDataRepository: LocalDataRepository)
            = GetUsersUseCase(localDataRepository)

    @Singleton
    @Provides
    fun provideSetActualUserUseCase(sharedPreferencesManager: SharedPreferencesManager)
            = SetActualUserUseCase(sharedPreferencesManager)

    @Singleton
    @Provides
    fun provideUpdateUserUseCase(localDataRepository: LocalDataRepository)
            = UpdateUserUserCase(localDataRepository)

    @Singleton
    @Provides
    fun provideAddGameResultUseCase(preferencesManager: SharedPreferencesManager,
                                    localDataRepository: LocalDataRepository,
                                    timeProvider: TimeProvider)
            = AddGameResultUseCase(preferencesManager, localDataRepository, timeProvider)

    @Singleton
    @Provides
    fun provideGetLeaderboardEntriesUseCase(localDataRepository: LocalDataRepository)
            = GetLeaderboardEntriesUseCase(localDataRepository)

    @Singleton
    @Provides
    fun provideGetNewQuestionsUseCase(triviaService: TriviaService)
            = GetNewQuestionsUseCase(triviaService)

}
