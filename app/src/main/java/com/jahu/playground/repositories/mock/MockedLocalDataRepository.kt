package com.jahu.playground.repositories.mock

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository

@SuppressWarnings("MagicNumber")
object MockedLocalDataRepository : LocalDataRepository {

    private val usersMap: MutableMap<String, User> = mutableMapOf()
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

    override fun addGameResult(gameResult: GameResult) {
        resultsList.add(gameResult)
    }

    override fun getGameResults(): List<GameResult> {
        return resultsList
    }

    override fun getGameResultsByNick(nick: String): List<GameResult> {
        return resultsList.filter { it.nick == nick }
    }
}
