package com.jahu.playground.usecases.users

import com.jahu.playground.dao.User
import com.jahu.playground.features.adduser.AddUserContract.ErrorCode
import com.jahu.playground.repositories.LocalDataRepository

class AddUserUseCase(
        private val dataRepository: LocalDataRepository
) {

    fun execute(user: User, resultListener: ResultListener) {
        val existingUser = dataRepository.getUserByNick(user.nick)
        if (existingUser == null) {
            dataRepository.addUser(user)
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
