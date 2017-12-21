package com.jahu.playground.usecases.users

import com.jahu.playground.data.DataSource
import com.jahu.playground.data.entities.User

class GetUsersUseCase(
        private val dataSource: DataSource
) {

    fun execute(): Set<User> {
        return dataSource.getAllUsers()
    }

}
