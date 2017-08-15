package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.mvp.BasePresenter

interface ChooseUserContract {

    interface View {

        fun showNoUsersMessage()

        fun showUsersList(users: List<User>)

        fun showNewUserInput()

        fun makeNewUserInputEditable()

        fun navigateToApp()

        fun showError(error: Error)

    }

    interface Presenter : BasePresenter {

        fun onExistingUserChosen(user: User)

        fun onNewUserInputClicked()

        fun onNewUserNameEntered(name: String)

    }

    enum class Error {
        EMPTY_NAME,
        EXISTING_NAME
    }

}