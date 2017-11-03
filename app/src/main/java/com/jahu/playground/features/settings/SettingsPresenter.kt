package com.jahu.playground.features.settings

import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.usecases.users.SetActualUserUseCase

class SettingsPresenter(
        private val view: SettingsContract.View,
        private val setActualUserUseCase: SetActualUserUseCase
) : SettingsContract.Presenter {

    override fun createView() {}

    override fun resumeView() {}

    override fun onLogoutButtonClicked() {
        setActualUserUseCase.execute(-1)
        view.logout()
    }

    override fun onEditUserButtonClicked() {
        view.navigateToEditUserScreen(EditUserContract.Mode.EDIT_USER)
    }
}
