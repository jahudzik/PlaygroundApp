package com.jahu.playground.features.leaderboard

import com.jahu.playground.di.FeatureScope
import com.jahu.playground.usecases.games.GetLeaderboardEntriesUseCase
import dagger.Module
import dagger.Provides

@Module
class LeaderboardModule(
        private val view: LeaderboardContract.View
) {

    @FeatureScope
    @Provides
    fun providePresenter(getLeaderboardEntriesUseCase: GetLeaderboardEntriesUseCase): LeaderboardContract.Presenter
            = LeaderboardPresenter(view, getLeaderboardEntriesUseCase)

}
