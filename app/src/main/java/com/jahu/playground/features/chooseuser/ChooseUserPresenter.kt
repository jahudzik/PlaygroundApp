package com.jahu.playground.features.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.mvp.BasePresenter
import com.jahu.playground.usecases.users.GetActualUserUseCase
import com.jahu.playground.usecases.users.GetUsersUseCase
import com.jahu.playground.usecases.users.SetActualUserUseCase

class ChooseUserPresenter(
        private val view: ChooseUserContract.View,
        private val getActualUserUseCase: GetActualUserUseCase,
        private val getUsersUseCase: GetUsersUseCase,
        private val setActualUserUseCase: SetActualUserUseCase
) : ChooseUserContract.Presenter, BasePresenter<ChooseUserContract.View>() {

    override fun resumeView() {
        val user = getActualUserUseCase.execute()
        if (user != null) {
            view.navigateToApp()
        } else {
            initUsersList()
        }
    }

    private fun initUsersList() {
        val users = getUsersUseCase.execute()
        if (users.isEmpty()) {
            view.showNoUsersMessage()
        } else {
            val sortedUsers = users.toList().sortedBy { it.nick }
            view.showUsersList(sortedUsers)
        }
    }

    override fun onAddUserButtonClicked() {
        view.navigateToEditUserScreen(EditUserContract.Mode.ADD_USER)
    }

    override fun onUserChosen(user: User) {
        setActualUserUseCase.execute(user.id)
        view.navigateToApp()
    }

}
