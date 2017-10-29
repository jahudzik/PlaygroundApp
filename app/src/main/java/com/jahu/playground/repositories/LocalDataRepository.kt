package com.jahu.playground.repositories

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User

interface LocalDataRepository {

    fun getAllUsers(): Set<User>

    fun getUserByNick(nick: String): User?

    fun addUser(user: User)

    fun addGameResult(gameResult: GameResult)

    fun getGameResults(): List<GameResult>

    fun getGameResultsByNick(nick: String): List<GameResult>

}
