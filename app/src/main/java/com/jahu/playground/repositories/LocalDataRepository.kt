package com.jahu.playground.repositories

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User

interface LocalDataRepository {

    fun getAllUsers(): Set<User>

    fun getUserById(id: Long): User?

    fun getUserByNick(nick: String): User?

    fun getHighestUserId(): Long?

    fun addUser(user: User)

    fun addGameResult(gameResult: GameResult)

    fun getGameResults(): List<GameResult>

    fun getGameResultsById(userId: Long): List<GameResult>

}
