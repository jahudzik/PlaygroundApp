package com.jahu.playground.repository.mock

import com.jahu.playground.dao.User
import com.jahu.playground.repository.LocalDataRepository

@SuppressWarnings("MagicNumber")
class MockedLocalDataRepository : LocalDataRepository {

    override fun getAllUsers(): List<User> {
        return listOf(
                User(1, "Ania"),
                User(2, "Tomek"),
                User(3, "Marek"))
    }

    override fun addUser(name: String) {
        TODO("not implemented")
    }
}
