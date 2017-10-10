package com.jahu.playground.repositories

import com.jahu.playground.dao.User

interface LocalDataRepository {

    fun getAllUsers(): Set<User>

    fun getUserByNick(nick: String): User?

    fun addUser(user: User)

}
