package com.jahu.playground.usecases.users

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager

class GetActualUserUseCase(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val localDataRepository: LocalDataRepository
) {

    fun execute() : User? {
        val userId = sharedPreferencesManager.getActualUserId()
        return if (userId != -1L) localDataRepository.getUserById(userId) else null
    }

}
