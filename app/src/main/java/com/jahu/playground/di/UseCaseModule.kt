package com.jahu.playground.di

import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.repositories.DataSource
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
    fun provideAddUserUseCase(dataSource: DataSource) = AddUserUseCase(dataSource)

    @Singleton
    @Provides
    fun provideGetActualUserUseCase(dataSource: DataSource,
                                    sharedPreferencesManager: SharedPreferencesManager)
            = GetActualUserUseCase(sharedPreferencesManager, dataSource)

    @Singleton
    @Provides
    fun provideGetUsersUseCase(dataSource: DataSource) = GetUsersUseCase(dataSource)

    @Singleton
    @Provides
    fun provideSetActualUserUseCase(sharedPreferencesManager: SharedPreferencesManager)
            = SetActualUserUseCase(sharedPreferencesManager)

    @Singleton
    @Provides
    fun provideUpdateUserUseCase(dataSource: DataSource) = UpdateUserUserCase(dataSource)

    @Singleton
    @Provides
    fun provideAddGameResultUseCase(preferencesManager: SharedPreferencesManager,
                                    dataSource: DataSource,
                                    timeProvider: TimeProvider)
            = AddGameResultUseCase(preferencesManager, dataSource, timeProvider)

    @Singleton
    @Provides
    fun provideGetLeaderboardEntriesUseCase(dataSource: DataSource) = GetLeaderboardEntriesUseCase(dataSource)

    @Singleton
    @Provides
    fun provideGetNewQuestionsUseCase(triviaService: TriviaService) = GetNewQuestionsUseCase(triviaService)

}
