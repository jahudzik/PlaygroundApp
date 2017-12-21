package com.jahu.playground.usecases.users

import com.jahu.playground.data.DataSource
import com.jahu.playground.data.entities.User
import com.jahu.playground.features.edituser.EditUserContract.ErrorCode

class AddUserUseCase(
        private val dataSource: DataSource
) {

    fun execute(firstName: String, lastName: String, nick: String, resultListener: ResultListener) {
        val existingUser = dataSource.getUserByNick(nick)
        if (existingUser == null) {
            val userId = generateUserId()
            dataSource.addUser(User(userId, firstName, lastName, nick))
            resultListener.onSuccess()
        } else {
            resultListener.onFailure(ErrorCode.NICK_EXISTS)
        }
    }

    private fun generateUserId(): Long {
        val highestUserId = dataSource.getHighestUserId()
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
