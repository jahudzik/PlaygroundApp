package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.usecases.GetUsersUseCase

class ChooseUserPresenter(
        private val view: ChooseUserContract.View,
        private val getUsersUseCase: GetUsersUseCase
) : ChooseUserContract.Presenter {

    override fun resumeView() {
        val users = getUsersUseCase.execute()
        if (users.isEmpty()) {
            view.showNoUsersMessage()
        } else {
            val sortedUsers = users.toList().sortedBy { it.nick }
            view.showUsersList(sortedUsers)
        }
    }

    override fun onUserChosen(user: User) {
        view.navigateToApp()
    }

    override fun onAddUserButtonClicked() {
        view.navigateToAddUserScreen()
    }

}
