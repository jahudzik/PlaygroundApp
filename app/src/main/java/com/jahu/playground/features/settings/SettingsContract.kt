package com.jahu.playground.features.settings

import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.MvpPresenter
import com.jahu.playground.mvp.MvpView

interface SettingsContract {

    interface View : MvpView {

        fun navigateToEditUserScreen(mode: EditUserContract.Mode)

        fun logout()

    }

    interface Presenter : MvpPresenter {

        fun onEditUserButtonClicked()

        fun onLogoutButtonClicked()

    }

}
