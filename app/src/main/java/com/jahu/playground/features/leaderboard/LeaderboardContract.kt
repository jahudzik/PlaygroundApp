package com.jahu.playground.features.leaderboard

import com.jahu.playground.data.entities.LeaderboardEntry
import com.jahu.playground.mvp.MvpPresenter
import com.jahu.playground.mvp.MvpView

interface LeaderboardContract {

    interface View : MvpView {

        fun showLeaderboardEntries(entries: List<LeaderboardEntry>)

    }

    interface Presenter : MvpPresenter

}
