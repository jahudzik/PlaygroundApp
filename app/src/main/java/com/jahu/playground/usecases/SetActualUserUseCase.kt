package com.jahu.playground.usecases

import com.jahu.playground.repositories.SharedPreferencesManager

class SetActualUserUseCase(
        private val preferencesManager: SharedPreferencesManager
) {

    fun execute(nick: String) {
        preferencesManager.setActualUserNick(nick)
    }

}
