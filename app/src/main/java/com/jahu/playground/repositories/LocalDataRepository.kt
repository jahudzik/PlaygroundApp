package com.jahu.playground.repositories

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User

interface LocalDataRepository {

    fun getAllUsers(): Set<User>

    fun getUserById(id: Long): User?

    fun getUserByNick(nick: String): User?

    fun getHighestUserId(): Long?

    fun addUser(user: User): OperationResult

    fun updateUser(user: User): OperationResult

    fun addGameResult(gameResult: GameResult)

    fun getGameResults(): List<GameResult>

    fun getGameResultsById(userId: Long): List<GameResult>

    fun reset()

    enum class OperationResult {
        SUCCESS,
        FAILURE_USER_EXISTS,
        FAILURE_USER_NOT_EXISTS
    }

}
