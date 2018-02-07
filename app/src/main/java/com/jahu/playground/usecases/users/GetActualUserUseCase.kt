package com.jahu.playground.usecases.users

import com.jahu.playground.data.DataSource
import com.jahu.playground.data.SharedPreferencesManager
import com.jahu.playground.data.entities.User

class GetActualUserUseCase(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val dataSource: DataSource
) {

    fun execute() : User? {
        val userId = sharedPreferencesManager.getActualUserId()
        return if (userId != -1L) {
            dataSource.getUserById(userId) ?: throw IllegalStateException("No data of the actual user found")
        } else {
            null
        }
    }

}
