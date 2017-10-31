package com.jahu.playground.features.settings

import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.BasePresenter

interface SettingsContract {

    interface View {

        fun navigateToEditUserScreen(mode: EditUserContract.Mode)

        fun logout()

    }

    interface Presenter : BasePresenter {

        fun onEditUserButtonClicked()

        fun onLogoutButtonClicked()

    }

}
