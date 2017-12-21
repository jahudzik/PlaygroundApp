package com.jahu.playground.usecases.games

import com.jahu.playground.data.LeaderboardEntry
import com.jahu.playground.extensions.roundTo2DecimalPlaces
import com.jahu.playground.repositories.DataSource

class GetLeaderboardEntriesUseCase(
        private val dataSource: DataSource
) {

    fun execute(): List<LeaderboardEntry> {
        val allResults = dataSource.getGameResults()
        return dataSource.getAllUsers()
                .map { user ->
                    val userGames = allResults.filter { it.userId == user.id }
                    val gamesCount = userGames.count()
                    val averageScore = if (gamesCount > 0) userGames.sumBy { it.correctAnswersCount }.toDouble() / gamesCount else 0.0
                    LeaderboardEntry(
                            user.nick,
                            averageScore.roundTo2DecimalPlaces(),
                            gamesCount
                    )
                }
                .sortedByDescending { it.averageScore }
    }

}
