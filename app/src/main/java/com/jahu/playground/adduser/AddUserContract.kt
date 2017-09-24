package com.jahu.playground.adduser

import com.jahu.playground.mvp.BasePresenter

interface AddUserContract {

    interface View {

        fun setAddButtonEnabled(enabled: Boolean)

        fun showErrorMessage(errorCode: ErrorCode)

        fun close()

    }

    interface Presenter : BasePresenter {

        fun onFieldValueChanged(firstName: String, lastName: String, nick: String)

        fun onAddButtonClicked(firstName: String, lastName: String, nick: String)

    }

    enum class ErrorCode {
        USER_EXISTS
    }

}
