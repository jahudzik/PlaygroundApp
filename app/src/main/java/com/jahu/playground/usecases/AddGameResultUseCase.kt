package com.jahu.playground.usecases

import com.jahu.playground.dao.GameResult
import com.jahu.playground.features.quiz.time.TimeProvider
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import timber.log.Timber

class AddGameResultUseCase(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val localDataRepository: LocalDataRepository,
        private val timeProvider: TimeProvider
) {

    fun execute(correctAnswersCount: Int) {
        val userNick = sharedPreferencesManager.getActualUserNick()
        if (userNick != null) {
            val gameResult = GameResult(userNick, correctAnswersCount, timeProvider.getCurrentTimestamp())
            localDataRepository.addGameResult(gameResult)
        } else {
            Timber.e("Failed to add game result - null user nick")
        }
    }

}
