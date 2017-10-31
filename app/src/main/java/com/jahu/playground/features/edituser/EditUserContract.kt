package com.jahu.playground.features.edituser

import com.jahu.playground.dao.User
import com.jahu.playground.mvp.BasePresenter

interface EditUserContract {

    interface View {

        fun fillUserData(user: User)

        fun setAddButtonLabel()

        fun setSaveButtonLabel()

        fun setConfirmButtonEnabled(enabled: Boolean)

        fun showErrorMessage(errorCode: ErrorCode)

        fun close()

    }

    interface Presenter : BasePresenter {

        fun onFieldValueChanged(firstName: String, lastName: String, nick: String)

        fun onConfirmButtonClicked(firstName: String, lastName: String, nick: String)

    }

    enum class Mode {
        ADD_USER,
        EDIT_USER
    }

    enum class ErrorCode {
        USER_EXISTS
    }

}
