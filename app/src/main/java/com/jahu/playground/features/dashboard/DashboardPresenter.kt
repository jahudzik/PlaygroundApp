package com.jahu.playground.features.dashboard

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager

class DashboardPresenter(
        private val view: DashboardContract.View,
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val dataRepository: LocalDataRepository
) : DashboardContract.Presenter {

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
