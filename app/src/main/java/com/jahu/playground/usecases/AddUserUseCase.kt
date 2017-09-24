package com.jahu.playground.usecases

import com.jahu.playground.adduser.AddUserContract.ErrorCode
import com.jahu.playground.dao.User
import com.jahu.playground.repository.LocalDataRepository

class AddUserUseCase(
        private val dataRepository: LocalDataRepository
) {

    fun execute(user: User, resultListener: ResultListener) {
        val existingUser = dataRepository.getUserByNick(user.nick)
        if (existingUser == null) {
            dataRepository.addUser(user)
            resultListener.onSuccess()
        } else {
            resultListener.onError(ErrorCode.USER_EXISTS)
        }
    }

    interface ResultListener {

        fun onSuccess()

        fun onError(errorCode: ErrorCode)
    }

}
