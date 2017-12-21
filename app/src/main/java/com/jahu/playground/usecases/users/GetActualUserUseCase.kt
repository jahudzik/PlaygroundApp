package com.jahu.playground.usecases.users

import com.jahu.playground.data.User
import com.jahu.playground.repositories.DataSource
import com.jahu.playground.repositories.SharedPreferencesManager

class GetActualUserUseCase(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val dataSource: DataSource
) {

    fun execute() : User? {
        val userId = sharedPreferencesManager.getActualUserId()
        return if (userId != -1L) dataSource.getUserById(userId) else null
    }

}
