package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User

class ChooseUserPresenter(
        val view: ChooseUserContract.View,
        val getUsersUseCase: GetUsersUseCase,
        val addUserUseCase: AddUserUseCase) : ChooseUserContract.Presenter {

    override fun initView() {
        val users = getUsersUseCase.execute()
        if (users.isEmpty()) {
            view.showNoUsersMessage()
        } else {
            view.showUsersList(users)
        }
        view.showNewUserInput()
    }

    override fun onExistingUserChosen(user: User) {
        view.navigateToApp()
    }

    override fun onNewUserInputClicked() {
        view.makeNewUserInputEditable()
    }

    override fun onNewUserNameEntered(name: String) {
        if (name.isEmpty()) {
            view.showError(ChooseUserContract.Error.EMPTY_NAME)
        } else {
            val users = getUsersUseCase.execute()
            if (users.map { user -> user.name }.contains(name)) {
                view.showError(ChooseUserContract.Error.EXISTING_NAME)
            } else {
                addUserUseCase.execute(name)
                view.navigateToApp()
            }
        }
    }
}