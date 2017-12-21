package com.jahu.playground.usecases.users

import com.jahu.playground.data.DataSource
import com.jahu.playground.data.entities.User
import com.jahu.playground.features.edituser.EditUserContract

class UpdateUserUserCase(
        private val dataSource: DataSource
) {

    fun execute(user: User, resultListener: ResultListener) {
        val userEntry = dataSource.getUserById(user.id)
        if (userEntry != null) {
            val userWithNick = dataSource.getUserByNick(user.nick)
            if (userWithNick == null || userWithNick.id == user.id) {
                dataSource.updateUser(user)
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
