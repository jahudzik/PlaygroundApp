package com.jahu.playground.repository.mock

import com.jahu.playground.dao.User
import com.jahu.playground.repository.LocalDataRepository

class MockedLocalDataRepository : LocalDataRepository {

    companion object {
        val USER1 = User("mike66", "Mike", "Jones")
        val USER2 = User("hippo", "Alice", "McMaster")
        val USER3 = User("barb", "Barbara", "Summers")
    }

    private val usersMap: MutableMap<String, User> = mutableMapOf()

    init {
        addUser(USER1)
        addUser(USER2)
        addUser(USER3)
    }

    override fun getAllUsers(): Set<User> {
        return usersMap.values.toSet()
    }

    override fun getUserByNick(nick: String): User? {
        return usersMap[nick]
    }

    override fun addUser(user: User) {
        usersMap.put(user.nick, user)
    }
}
