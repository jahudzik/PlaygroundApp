package com.jahu.playground.chooseuser

import com.jahu.playground.repository.LocalDataRepository

class AddUserUseCase(
        val localDataRepository: LocalDataRepository
) {

    fun execute(name: String) {
        localDataRepository.addUser(name)
    }

}