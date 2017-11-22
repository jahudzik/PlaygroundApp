package com.jahu.playground.repositories.memory

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository

open class InMemoryLocalDataRepository : LocalDataRepository {

    private val usersMap: MutableMap<Long, User> = mutableMapOf()
    private val resultsList: MutableList<GameResult> = mutableListOf()

    override fun reset() {
        usersMap.clear()
        resultsList.clear()
    }

    override fun getAllUsers() = usersMap.values.toSet()

    override fun getHighestUserId(): Long? {
        return if (usersMap.isNotEmpty()) {
            usersMap.values.map { it.id }.sorted().last()
        } else {
            null
        }
    }

    override fun getUserById(id: Long) = usersMap[id]

    override fun getUserByNick(nick: String): User? {
        val users = usersMap.values.filter { it.nick == nick }
        return if (users.isNotEmpty()) users.first() else null
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

    override fun getGameResultsById(userId: Long) = resultsList.filter { it.userId == userId }

}
