package com.jahu.playground.features.edituser

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract.ErrorCode
import com.jahu.playground.usecases.users.AddUserUseCase
import com.jahu.playground.usecases.users.GetActualUserUseCase
import com.jahu.playground.usecases.users.UpdateUserUserCase

class EditUserPresenter(
        private val mode: EditUserContract.Mode,
        private val view: EditUserContract.View,
        private val addUserUseCase: AddUserUseCase,
        private val getActualUserUseCase: GetActualUserUseCase,
        private val updateUserUserCase: UpdateUserUserCase
) : EditUserContract.Presenter {

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
        if (mode == EditUserContract.Mode.ADD_USER) {
            addUser(firstName, lastName, nick)
        } else if (mode == EditUserContract.Mode.EDIT_USER) {
            updateUser(firstName, lastName, nick)
        }

    }

    private fun addUser(firstName: String, lastName: String, nick: String) {
        addUserUseCase.execute(firstName, lastName, nick, object : AddUserUseCase.ResultListener {
            override fun onSuccess() {
                view.close()
            }

            override fun onFailure(errorCode: ErrorCode) {
                view.showErrorMessage(errorCode)
            }
        })
    }

    private fun updateUser(firstName: String, lastName: String, nick: String) {
        getActualUserUseCase.execute()?.let {
            updateUserUserCase.execute(User(it.id, firstName, lastName, nick), object : UpdateUserUserCase.ResultListener {
                override fun onSuccess() {
                    view.close()
                }

                override fun onFailure(errorCode: ErrorCode) {
                    view.showErrorMessage(errorCode)
                }

            })
        }
    }

}
