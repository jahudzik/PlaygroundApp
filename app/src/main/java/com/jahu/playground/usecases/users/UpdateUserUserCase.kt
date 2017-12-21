package com.jahu.playground.usecases.users

import com.jahu.playground.data.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.repositories.LocalDataRepository

class UpdateUserUserCase(
        private val dataRepository: LocalDataRepository
) {

    fun execute(user: User, resultListener: ResultListener) {
        val userEntry = dataRepository.getUserById(user.id)
        if (userEntry != null) {
            val userWithNick = dataRepository.getUserByNick(user.nick)
            if (userWithNick == null || userWithNick.id == user.id) {
                dataRepository.updateUser(user)
                resultListener.onSuccess()
            } else {
                resultListener.onFailure(EditUserContract.ErrorCode.NICK_EXISTS)
            }
        } else {
            resultListener.onFailure(EditUserContract.ErrorCode.USER_NOT_EXISTS)
        }
    }

    interface ResultListener {

        fun onSuccess()

        fun onFailure(errorCode: EditUserContract.ErrorCode)
    }

}
