package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.repository.LocalDataRepository

class GetUsersUseCase(
        private val dataRepository: LocalDataRepository
) {

    fun execute(): Set<User> {
        return dataRepository.getAllUsers()
    }

}
