package com.jahu.playground.repositories.mock

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository

@SuppressWarnings("MagicNumber")
object MockedLocalDataRepository : LocalDataRepository {

    private val usersMap: MutableMap<Long, User> = mutableMapOf()
    private val resultsList: MutableList<GameResult> = mutableListOf()

    val user1 = User(1, "Mike", "Jones", "mike66")
    val user2 = User(2, "Alice", "McMaster", "hippo")
    val user3 = User(3, "Barbara", "Summers", "barb")

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
        resultsList.clear()
        fillMockData()
    }

    override fun getAllUsers(): Set<User> {
        return usersMap.values.toSet()
    }

    override fun getHighestUserId(): Long? {
        return if (usersMap.isNotEmpty()) {
            usersMap.values.map { it.id }.sorted().last()
        } else {
            null
        }
    }

    override fun getUserById(id: Long): User? {
        return usersMap[id]
    }

    override fun getUserByNick(nick: String): User? {
        val users = usersMap.values.filter { it.nick == nick }
        return if (users.isNotEmpty()) users.first() else null
    }

    override fun addUser(user: User) {
        usersMap.put(user.id, user)
    }

    override fun addGameResult(gameResult: GameResult) {
        resultsList.add(gameResult)
    }

    override fun getGameResults(): List<GameResult> {
        return resultsList
    }

    override fun getGameResultsById(userId: Long): List<GameResult> {
        return resultsList.filter { it.userId == userId }
    }
}
