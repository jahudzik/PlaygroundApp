package com.jahu.playground.features.dashboard

import com.jahu.playground.mvp.MvpPresenter
import com.jahu.playground.mvp.MvpView

interface DashboardContract {

    interface View : MvpView {

        fun showBottomNavigationBar(items: List<BottomNavigationItem>)

        fun showQuizSetup()

        fun showLeaderboard()

        fun showSettings()

    }

    interface Presenter : MvpPresenter {

        fun onNavigationItemSelected(itemId: Int): Boolean

    }

}
