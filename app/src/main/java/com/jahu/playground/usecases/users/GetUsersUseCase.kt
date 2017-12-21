package com.jahu.playground.usecases.users

import com.jahu.playground.data.User
import com.jahu.playground.repositories.LocalDataRepository

class GetUsersUseCase(
        private val dataRepository: LocalDataRepository
) {

    fun execute(): Set<User> {
        return dataRepository.getAllUsers()
    }

}
