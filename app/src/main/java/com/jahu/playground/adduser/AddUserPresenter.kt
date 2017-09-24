package com.jahu.playground.adduser

import com.jahu.playground.dao.User
import com.jahu.playground.usecases.AddUserUseCase
import com.jahu.playground.adduser.AddUserContract.ErrorCode

class AddUserPresenter(
        private val view: AddUserContract.View,
        private val addUserUseCase: AddUserUseCase
) : AddUserContract.Presenter, AddUserUseCase.ResultListener {

    override fun initView() {
        view.setAddButtonEnabled(false)
    }

    override fun onFieldValueChanged(firstName: String, lastName: String, nick: String) {
        if (firstName.isEmpty() || lastName.isEmpty() || nick.isEmpty()) {
            view.setAddButtonEnabled(false)
        } else {
            view.setAddButtonEnabled(true)
        }
    }

    override fun onAddButtonClicked(firstName: String, lastName: String, nick: String) {
        addUserUseCase.execute(User(firstName, lastName, nick), this)
    }

    override fun onSuccess() {
        view.close()
    }

    override fun onError(errorCode: ErrorCode) {
        view.showErrorMessage(errorCode)
    }
}