package com.jahu.playground.usecases

import com.jahu.playground.dao.LeaderboardEntry
import com.jahu.playground.repositories.LocalDataRepository

class GetLeaderboardEntriesUseCase(
        private val localDataRepository: LocalDataRepository
) {

    fun execute(): List<LeaderboardEntry> {
        val allResults = localDataRepository.getGameResults()
        if (allResults.isEmpty()) {
            return emptyList()
        }
        return localDataRepository.getAllUsers()
                .map { user ->
                    val userGames = allResults.filter { it.nick == user.nick }
                    LeaderboardEntry(
                            user.nick,
                            userGames.sumBy { it.correctAnswersCount }.toDouble() / userGames.count(),
                            userGames.count()
                    )
                }
                .sortedByDescending { it.averageScore }
    }

}
