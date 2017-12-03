package com.jahu.playground.features.gamesetup

import com.jahu.playground.di.FeatureScope
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.usecases.games.GetNewQuestionsUseCase
import dagger.Module
import dagger.Provides

@Module
class GameSetupModule(
        private val view: GameSetupContract.View
) {

    @FeatureScope
    @Provides
    fun providePresenter(
            sharedPreferencesManager: SharedPreferencesManager,
            localDataRepository: LocalDataRepository,
            getNewQuestionsUseCase: GetNewQuestionsUseCase
    ): GameSetupContract.Presenter
            = GameSetupPresenter(view, sharedPreferencesManager, localDataRepository, getNewQuestionsUseCase)

}
