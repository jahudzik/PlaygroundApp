package com.jahu.playground.features.edituser

import com.jahu.playground.mvp.BasePresenter

interface EditUserContract {

    interface View {

        fun setConfirmButtonEnabled(enabled: Boolean)

        fun showErrorMessage(errorCode: ErrorCode)

        fun close()

    }

    interface Presenter : BasePresenter {

        fun onFieldValueChanged(firstName: String, lastName: String, nick: String)

        fun onConfirmButtonClicked(firstName: String, lastName: String, nick: String)

    }

    enum class ErrorCode {
        USER_EXISTS
    }

}
