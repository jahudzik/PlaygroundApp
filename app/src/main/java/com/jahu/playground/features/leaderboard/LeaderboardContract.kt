package com.jahu.playground.features.leaderboard

import com.jahu.playground.dao.LeaderboardEntry
import com.jahu.playground.mvp.BasePresenter

interface LeaderboardContract {

    interface View {

        fun showLeaderboardEntries(entries: List<LeaderboardEntry>)

    }

    interface Presenter : BasePresenter

}
