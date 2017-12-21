package com.jahu.playground.features.chooseuser

import com.jahu.playground.data.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.MvpPresenter
import com.jahu.playground.mvp.MvpView

interface ChooseUserContract {

    interface View : MvpView {

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
