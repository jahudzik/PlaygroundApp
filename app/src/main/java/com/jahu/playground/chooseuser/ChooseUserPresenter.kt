package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User

class ChooseUserPresenter(
        private val view: ChooseUserContract.View,
        private val getUsersUseCase: GetUsersUseCase
) : ChooseUserContract.Presenter {

    override fun initView() {
        val users = getUsersUseCase.execute()
        if (users.isEmpty()) {
            view.showNoUsersMessage()
        } else {
            view.showUsersList(users)
        }
    }

    override fun onUserChosen(user: User) {
        view.navigateToApp()
    }

    override fun onAddUserButtonClicked() {
        view.navigateToAddUserScreen()
    }

}