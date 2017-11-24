package com.jahu.playground.features.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.MvpPresenter

interface ChooseUserContract {

    interface View {

        fun showNoUsersMessage()

        fun showUsersList(users: List<User>)

        fun navigateToEditUserScreen(mode: EditUserContract.Mode)

        fun navigateToApp()

    }

    interface Presenter : MvpPresenter {

        fun onUserChosen(user: User)

        fun onAddUserButtonClicked()

    }

}
