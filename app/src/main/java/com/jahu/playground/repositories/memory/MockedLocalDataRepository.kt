package com.jahu.playground.repositories.memory

import com.jahu.playground.dao.User

@SuppressWarnings("MagicNumber")
object MockedLocalDataRepository : InMemoryLocalDataRepository() {

    val user1 = User(1, "Mike", "Jones", "mike66")
    val user2 = User(2, "Alice", "McMaster", "hippo")
    val user3 = User(3, "Barbara", "Summers", "barb")

    init {
        fillMockData()
    }

    override fun reset() {
        super.reset()
        fillMockData()
    }

    private fun fillMockData() {
        addUser(user1)
        addUser(user2)
        addUser(user3)
    }

}
