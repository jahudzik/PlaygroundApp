package com.jahu.playground.usecases.users

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract.ErrorCode
import com.jahu.playground.repositories.LocalDataRepository

class AddUserUseCase(
        private val dataRepository: LocalDataRepository
) {

    fun execute(firstName: String, lastName: String, nick: String, resultListener: ResultListener) {
        val existingUser = dataRepository.getUserByNick(nick)
        if (existingUser == null) {
            val userId = generateUserId()
            dataRepository.addUser(User(userId, firstName, lastName, nick))
            resultListener.onSuccess()
        } else {
            resultListener.onFailure(ErrorCode.NICK_EXISTS)
        }
    }

    private fun generateUserId(): Long {
        val highestUserId = dataRepository.getHighestUserId()
        return if (highestUserId != null) {
            highestUserId + 1
        } else {
            1
        }
    }

    interface ResultListener {

        fun onSuccess()

        fun onFailure(errorCode: ErrorCode)
    }

}
