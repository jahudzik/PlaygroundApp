package com.jahu.playground.usecases.users

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager

class GetActualUserUseCase(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val localDataRepository: LocalDataRepository
) {

    fun execute() : User? {
        val nick = sharedPreferencesManager.getActualUserNick()
        return if (nick != null) localDataRepository.getUserByNick(nick) else null
    }

}
