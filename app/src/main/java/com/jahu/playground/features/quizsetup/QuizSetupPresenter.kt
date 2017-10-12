package com.jahu.playground.features.quizsetup

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager

class QuizSetupPresenter(
        private val view: QuizSetupContract.View,
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val dataRepository: LocalDataRepository
) : QuizSetupContract.Presenter {

    override fun resumeView() {
        val user = getActualUser()
        view.showUserName(user.firstName)
    }

    private fun getActualUser(): User {
        val nick = sharedPreferencesManager.getActualUserNick() ?:
                throw IllegalStateException("No information about current user (nick)")
        return dataRepository.getUserByNick(nick) ?:
                throw IllegalStateException("No information about current user (user data)")
    }

}
