package com.jahu.playground.repository.mock

import com.jahu.playground.dao.User
import com.jahu.playground.repository.LocalDataRepository

class MockedLocalDataRepository : LocalDataRepository {

    companion object {
        val user1 = User("mike66", "Mike", "Jones")
        val user2 = User("hippo", "Alice", "McMaster")
        val user3 = User("barb", "Barbara", "Summers")
    }

    private val usersMap: MutableMap<String, User> = mutableMapOf()

    init {
        addUser(user1)
        addUser(user2)
        addUser(user3)
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
