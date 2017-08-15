package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User

class GetUsersUseCase(
        val getUsersUseCase: GetUsersUseCase
) {

    fun execute(): List<User> {
        return getUsersUseCase.execute()
    }

}