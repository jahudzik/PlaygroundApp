package com.jahu.playground.features.dashboard

import com.jahu.playground.mvp.BasePresenter

interface DashboardContract {

    interface View {

        fun showUserName(userName: String)
    }

    interface Presenter : BasePresenter

}
