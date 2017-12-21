package com.jahu.playground.usecases.users

import com.jahu.playground.data.SharedPreferencesManager

class SetActualUserUseCase(
        private val preferencesManager: SharedPreferencesManager
) {

    fun execute(userId: Long) {
        preferencesManager.setActualUserId(userId)
    }

}
