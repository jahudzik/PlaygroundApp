package com.jahu.playground.features.leaderboard

import com.jahu.playground.usecases.games.GetLeaderboardEntriesUseCase

class LeaderboardPresenter(
        private val view: LeaderboardContract.View,
        private val getLeaderboardEntriesUseCase: GetLeaderboardEntriesUseCase
) : LeaderboardContract.Presenter {

    override fun createView() {
        val entries = getLeaderboardEntriesUseCase.execute()
        view.showLeaderboardEntries(entries)
    }

    override fun resumeView() {}

}
