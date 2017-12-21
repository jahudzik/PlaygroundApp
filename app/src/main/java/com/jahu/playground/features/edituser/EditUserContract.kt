package com.jahu.playground.features.edituser

import com.jahu.playground.data.User
import com.jahu.playground.mvp.MvpPresenter
import com.jahu.playground.mvp.MvpView

interface EditUserContract {

    interface View : MvpView {

        fun fillUserData(user: User)

        fun setAddButtonLabel()

        fun setSaveButtonLabel()

        fun setConfirmButtonEnabled(enabled: Boolean)

        fun showErrorMessage(errorCode: ErrorCode)

        fun close()

    }

    interface Presenter : MvpPresenter {

        fun onFieldValueChanged(firstName: String, lastName: String, nick: String)

        fun onConfirmButtonClicked(firstName: String, lastName: String, nick: String)

    }

    enum class Mode {
        ADD_USER,
        EDIT_USER
    }

    enum class ErrorCode {
        NICK_EXISTS,
        USER_NOT_EXISTS
    }

}
