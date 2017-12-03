package com.jahu.playground.features.leaderboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jahu.playground.R
import com.jahu.playground.dao.LeaderboardEntry
import com.jahu.playground.mvp.MvpFragment
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import javax.inject.Inject

class LeaderboardFragment : MvpFragment<LeaderboardContract.Presenter>(), LeaderboardContract.View {

    @Inject override lateinit var presenter: LeaderboardContract.Presenter

    companion object {
        fun newInstance() = LeaderboardFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent()
                .plus(LeaderboardModule(this))
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun showLeaderboardEntries(entries: List<LeaderboardEntry>) {
        leaderboardRecyclerView.layoutManager = LinearLayoutManager(activity)
        leaderboardRecyclerView.adapter = LeaderboardAdapter(entries)
    }

}
