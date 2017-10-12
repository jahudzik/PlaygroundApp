package com.jahu.playground.features.dashboard

import com.jahu.playground.mvp.BasePresenter

interface DashboardContract {

    interface View {

        fun showBottomNavigationBar(items: List<BottomNavigationItem>)

        fun showQuizSetupScreen()

    }

    interface Presenter : BasePresenter

}
