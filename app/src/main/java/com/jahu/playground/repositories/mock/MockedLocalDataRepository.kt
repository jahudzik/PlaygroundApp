package com.jahu.playground.repositories.mock

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository

object MockedLocalDataRepository : LocalDataRepository {

    private val usersMap: MutableMap<String, User> = mutableMapOf()

    val user1 = User("Mike", "Jones", "mike66")
    val user2 = User("Alice", "McMaster", "hippo")
    val user3 = User("Barbara", "Summers", "barb")

    init {
        fillMockData()
    }

    private fun fillMockData() {
        addUser(user1)
        addUser(user2)
        addUser(user3)
    }

    fun reset() {
        usersMap.clear()
        fillMockData()
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
