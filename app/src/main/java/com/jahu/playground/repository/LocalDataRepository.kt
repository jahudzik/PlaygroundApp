package com.jahu.playground.repository

import com.jahu.playground.dao.User

interface LocalDataRepository {

    fun getAllUsers(): List<User>

    fun getUserByNick(nick: String): User?

    fun addUser(user: User)

}
