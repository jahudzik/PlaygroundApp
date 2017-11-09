package com.jahu.playground.repositories.mock

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

    override fun addUser(user: User): LocalDataRepository.OperationResult {
        if (usersMap.containsKey(user.id)) {
            return LocalDataRepository.OperationResult.FAILURE_USER_EXISTS
        } else {
            usersMap.put(user.id, user)
            return LocalDataRepository.OperationResult.SUCCESS
        }
    }

    override fun updateUser(user: User): LocalDataRepository.OperationResult {
        if (!usersMap.containsKey(user.id)) {
            return LocalDataRepository.OperationResult.FAILURE_USER_NOT_EXISTS
        } else {
            usersMap.put(user.id, user)
            return LocalDataRepository.OperationResult.SUCCESS
        }
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
