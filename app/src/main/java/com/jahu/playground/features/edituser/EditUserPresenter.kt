package com.jahu.playground.features.edituser

import com.jahu.playground.features.edituser.EditUserContract.ErrorCode
import com.jahu.playground.usecases.users.AddUserUseCase
import com.jahu.playground.usecases.users.GetActualUserUseCase

class EditUserPresenter(
        private val mode: EditUserContract.Mode,
        private val view: EditUserContract.View,
        private val addUserUseCase: AddUserUseCase,
        private val getActualUserUseCase: GetActualUserUseCase
) : EditUserContract.Presenter, AddUserUseCase.ResultListener {

    override fun createView() {}

    override fun resumeView() {
        when (mode) {
            EditUserContract.Mode.ADD_USER -> initAddUserMode()
            EditUserContract.Mode.EDIT_USER -> initEditUserMode()
        }
    }

    private fun initAddUserMode() {
        view.setAddButtonLabel()
        view.setConfirmButtonEnabled(false)
    }

    private fun initEditUserMode() {
        getActualUserUseCase.execute()?.let {
            view.fillUserData(it)
        }
        view.setSaveButtonLabel()
        view.setConfirmButtonEnabled(true)
    }

    override fun onFieldValueChanged(firstName: String, lastName: String, nick: String) {
        if (firstName.isEmpty() || lastName.isEmpty() || nick.isEmpty()) {
            view.setConfirmButtonEnabled(false)
        } else {
            view.setConfirmButtonEnabled(true)
        }
    }

    override fun onConfirmButtonClicked(firstName: String, lastName: String, nick: String) {
        addUserUseCase.execute(firstName, lastName, nick, this)
    }

    override fun onSuccess() {
        view.close()
    }

    override fun onFailure(errorCode: ErrorCode) {
        view.showErrorMessage(errorCode)
    }
}
