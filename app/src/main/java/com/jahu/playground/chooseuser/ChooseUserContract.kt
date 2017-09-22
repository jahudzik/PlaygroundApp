package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.mvp.BasePresenter

interface ChooseUserContract {

    interface View {

        fun showNoUsersMessage()

        fun showUsersList(users: List<User>)

        fun navigateToAddUserScreen()

        fun navigateToApp()

    }

    interface Presenter : BasePresenter {

        fun onUserChosen(user: User)

        fun onAddUserButtonClicked()

    }

}
