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
            dataRepository.addUser(User(1, firstName, lastName, nick))
            resultListener.onSuccess()
        } else {
            resultListener.onFailure(ErrorCode.USER_EXISTS)
        }
    }

    interface ResultListener {

        fun onSuccess()

        fun onFailure(errorCode: ErrorCode)
    }

}
