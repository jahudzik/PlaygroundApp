package com.jahu.playground.repositories.memory

import com.jahu.playground.data.GameResult
import com.jahu.playground.data.User
import com.jahu.playground.repositories.DataSource

open class InMemoryDataSource : DataSource {

    private val usersMap: MutableMap<Long, User> = mutableMapOf()
    private val resultsList: MutableList<GameResult> = mutableListOf()

    override fun getAllUsers() = usersMap.values.toSet()

    override fun getUserById(id: Long) = usersMap[id]

    override fun getUserByNick(nick: String): User? {
        val users = usersMap.values.filter { it.nick == nick }
        return if (users.isNotEmpty()) users.first() else null
    }

    override fun getHighestUserId(): Long? {
        return if (usersMap.isNotEmpty()) {
            usersMap.values.map { it.id }.sorted().last()
        } else {
            null
        }
    }

    override fun addUser(user: User) {
        usersMap.put(user.id, user)
    }

    override fun updateUser(user: User) {
        usersMap.put(user.id, user)
    }

    override fun addGameResult(gameResult: GameResult) {
        resultsList.add(gameResult)
    }

    override fun getGameResults() = resultsList

    override fun getGameResultsByUserId(userId: Long) = resultsList.filter { it.userId == userId }

    override fun reset() {
        usersMap.clear()
        resultsList.clear()
    }

}
