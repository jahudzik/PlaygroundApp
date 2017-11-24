package com.jahu.playground.features.settings

import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.MvpPresenter

interface SettingsContract {

    interface View {

        fun navigateToEditUserScreen(mode: EditUserContract.Mode)

        fun logout()

    }

    interface Presenter : MvpPresenter {

        fun onEditUserButtonClicked()

        fun onLogoutButtonClicked()

    }

}
