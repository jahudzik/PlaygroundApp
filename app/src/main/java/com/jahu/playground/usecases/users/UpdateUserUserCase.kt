package com.jahu.playground.usecases.users

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.repositories.LocalDataRepository

class UpdateUserUserCase(
        private val dataRepository: LocalDataRepository
) {

    fun execute(user: User, resultListener: ResultListener) {
        val existingUser = dataRepository.getUserByNick(user.nick)
        if (existingUser == null) {
            val result = dataRepository.updateUser(user)
            if (result == LocalDataRepository.OperationResult.FAILURE_USER_NOT_EXISTS) {
                resultListener.onFailure(EditUserContract.ErrorCode.USER_NOT_EXISTS)
            } else {
                resultListener.onSuccess()
            }
        } else {
            resultListener.onFailure(EditUserContract.ErrorCode.NICK_EXISTS)
        }
    }

    interface ResultListener {

        fun onSuccess()

        fun onFailure(errorCode: EditUserContract.ErrorCode)
    }

}
