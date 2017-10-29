package com.jahu.playground.usecases.games

import com.jahu.playground.dao.LeaderboardEntry
import com.jahu.playground.repositories.LocalDataRepository

class GetLeaderboardEntriesUseCase(
        private val localDataRepository: LocalDataRepository
) {

    fun execute(): List<LeaderboardEntry> {
        val allResults = localDataRepository.getGameResults()
        return localDataRepository.getAllUsers()
                .map { user ->
                    val userGames = allResults.filter { it.nick == user.nick }
                    val gamesCount = userGames.count()
                    val averageScore = if (gamesCount > 0) userGames.sumBy { it.correctAnswersCount }.toDouble() / gamesCount else 0.0
                    LeaderboardEntry(
                            user.nick,
                            averageScore,
                            gamesCount
                    )
                }
                .sortedByDescending { it.averageScore }
    }

}
