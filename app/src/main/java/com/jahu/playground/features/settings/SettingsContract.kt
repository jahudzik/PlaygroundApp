package com.jahu.playground.features.settings

import com.jahu.playground.mvp.BasePresenter

interface SettingsContract {

    interface View {

        fun logout()

    }

    interface Presenter : BasePresenter {

        fun onLogoutButtonClicked()

    }

}
