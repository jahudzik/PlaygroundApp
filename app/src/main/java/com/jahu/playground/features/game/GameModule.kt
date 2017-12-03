package com.jahu.playground.features.game

import com.jahu.playground.di.FeatureScope
import com.jahu.playground.features.game.random.RandomSequenceGenerator
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.games.AddGameResultUseCase
import dagger.Module
import dagger.Provides

@Module
class GameModule(
        private val view: GameContract.View,
        private val questions: List<TriviaQuestion>
) {

    @FeatureScope
    @Provides
    fun providePresenter(sequenceGenerator: RandomSequenceGenerator,
                         addGameResultUseCase: AddGameResultUseCase): GameContract.Presenter
            = GamePresenter(view, questions, sequenceGenerator, addGameResultUseCase)

}
