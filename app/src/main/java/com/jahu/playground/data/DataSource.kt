package com.jahu.playground.data

import com.jahu.playground.data.entities.GameResult
import com.jahu.playground.data.entities.User

interface DataSource {

    fun getAllUsers(): Set<User>

    fun getUserById(id: Long): User?

    fun getUserByNick(nick: String): User?

    fun getHighestUserId(): Long?

    fun addUser(user: User)

    fun updateUser(user: User)

    fun addGameResult(gameResult: GameResult)

    fun getGameResults(): List<GameResult>

    fun getGameResultsByUserId(userId: Long): List<GameResult>

    fun reset()

}
