package com.jahu.playground.features.dashboard

import com.jahu.playground.mvp.MvpPresenter

interface DashboardContract {

    interface View {

        fun showBottomNavigationBar(items: List<BottomNavigationItem>)

        fun showQuizSetup()

        fun showLeaderboard()

        fun showSettings()

    }

    interface Presenter : MvpPresenter {

        fun onNavigationItemSelected(itemId: Int): Boolean

    }

}
