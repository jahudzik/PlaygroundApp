package com.jahu.playground.usecases.games

import com.jahu.playground.data.DataSource
import com.jahu.playground.data.SharedPreferencesManager
import com.jahu.playground.data.entities.GameResult
import com.jahu.playground.features.game.time.TimeProvider
import timber.log.Timber

class AddGameResultUseCase(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val dataSource: DataSource,
        private val timeProvider: TimeProvider
) {

    fun execute(correctAnswersCount: Int) {
        val userId = sharedPreferencesManager.getActualUserId()
        if (userId != -1L) {
            val gameResult = GameResult(userId, correctAnswersCount, timeProvider.getCurrentTimestamp())
            dataSource.addGameResult(gameResult)
        } else {
            Timber.e("Failed to add game result - null user nick")
        }
    }

}
