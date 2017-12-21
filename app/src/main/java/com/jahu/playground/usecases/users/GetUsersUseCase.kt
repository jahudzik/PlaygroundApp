package com.jahu.playground.usecases.users

import com.jahu.playground.data.User
import com.jahu.playground.repositories.DataSource

class GetUsersUseCase(
        private val dataSource: DataSource
) {

    fun execute(): Set<User> {
        return dataSource.getAllUsers()
    }

}
