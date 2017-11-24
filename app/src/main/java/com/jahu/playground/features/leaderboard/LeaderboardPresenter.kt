package com.jahu.playground.features.leaderboard

import com.jahu.playground.mvp.BasePresenter
import com.jahu.playground.usecases.games.GetLeaderboardEntriesUseCase

class LeaderboardPresenter(
        private val view: LeaderboardContract.View,
        private val getLeaderboardEntriesUseCase: GetLeaderboardEntriesUseCase
) : LeaderboardContract.Presenter, BasePresenter<LeaderboardContract.View>() {

    override fun resumeView() {
        val entries = getLeaderboardEntriesUseCase.execute()
        view.showLeaderboardEntries(entries)
    }

}
