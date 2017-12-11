package com.jahu.playground.repositories.database

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.orm.SugarRecord
import com.orm.query.Select

class SugarOrmLocalDataRepository : LocalDataRepository {

    override fun getAllUsers(): Set<User> = SugarRecord.listAll(User::class.java).toSet()

    override fun getUserById(id: Long): User? = SugarRecord.findById(User::class.java, id)

    override fun getUserByNick(nick: String): User? {
        val users = SugarRecord.find(User::class.java, "nick = ?", nick)
        return if (users.isNotEmpty()) users.first() else null
    }

    override fun getHighestUserId(): Long? {
        val users = Select.from(User::class.java).orderBy("id").list()
        return if (users.isNotEmpty()) users.first().id else null
    }

    override fun addUser(user: User) {
        user.save()
    }

    override fun updateUser(user: User) {
        user.setId(user.id)  // Setting SugarORM internal identifier - otherwise save() will insert instead of updating
        user.save()
    }

    override fun addGameResult(gameResult: GameResult) {
        gameResult.save()
    }

    override fun getGameResults(): List<GameResult> = SugarRecord.listAll(GameResult::class.java)

    override fun getGameResultsByUserId(userId: Long): List<GameResult> =
            SugarRecord.find(GameResult::class.java, "user_id = ?", userId.toString())

    override fun reset() {
        SugarRecord.deleteAll(User::class.java)
        SugarRecord.deleteAll(GameResult::class.java)
    }

}
