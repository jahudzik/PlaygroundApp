package com.jahu.playground.usecases

import com.jahu.playground.dao.User
import com.jahu.playground.repository.LocalDataRepository
import com.jahu.playground.repository.SharedPreferencesManager

class GetActualUserUseCase(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val localDataRepository: LocalDataRepository
) {

    fun execute() : User? {
        val nick = sharedPreferencesManager.getActualUserNick()
        return if (nick != null) localDataRepository.getUserByNick(nick) else null
    }

}
