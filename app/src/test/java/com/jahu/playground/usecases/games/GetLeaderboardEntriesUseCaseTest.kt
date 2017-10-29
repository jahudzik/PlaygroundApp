package com.jahu.playground.usecases.games

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.LeaderboardEntry
import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetLeaderboardEntriesUseCaseTest {

    private lateinit var useCase: GetLeaderboardEntriesUseCase

    @Mock private lateinit var localDataRepository: LocalDataRepository

    private val testUsers = listOf(
            User("John", "Smith", "smithy"),
            User("Anna", "Brown", "anna"),
            User("Eric", "Newman", "eric"),
            User("Maria", "McDonald", "mary")
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetLeaderboardEntriesUseCase(localDataRepository)
    }

    @Test
    fun execute_singleUser_coupleGamesPlayed() {
        val userGames = buildGameResultsList(testUsers[0].nick, 8, 6, 7, 4)
        whenever(localDataRepository.getAllUsers()).thenReturn(setOf(testUsers[0]))
        whenever(localDataRepository.getGameResults()).thenReturn(userGames)

        val resultList = useCase.execute()

        verify(localDataRepository).getGameResults()
        verify(localDataRepository).getAllUsers()
        val expectedResult = listOf(LeaderboardEntry(testUsers[0].nick, 6.25, 4))
        assertEquals(expectedResult, resultList)
    }

    @Test
    fun execute_singleUser_noGamesPlayed() {
        whenever(localDataRepository.getAllUsers()).thenReturn(setOf(testUsers[0]))
        whenever(localDataRepository.getGameResults()).thenReturn(emptyList())

        val resultList = useCase.execute()

        verify(localDataRepository).getGameResults()
        verify(localDataRepository).getAllUsers()
        val expectedResult = listOf(LeaderboardEntry(testUsers[0].nick, 0.0, 0))
        assertEquals(expectedResult, resultList)
    }

    @Test
    fun execute_multipleUsers() {
        val userGames = buildGameResultsList(testUsers[0].nick, 7, 6, 1, 4, 5)
                .plus(buildGameResultsList(testUsers[1].nick, 1, 4, 5, 2, 1, 2))
                .plus(buildGameResultsList(testUsers[2].nick))
                .plus(buildGameResultsList(testUsers[3].nick, 10, 9, 8, 9))
        whenever(localDataRepository.getAllUsers()).thenReturn(testUsers.toSet())
        whenever(localDataRepository.getGameResults()).thenReturn(userGames)

        val resultList = useCase.execute()

        verify(localDataRepository).getGameResults()
        verify(localDataRepository).getAllUsers()
        val expectedResult = listOf(
                LeaderboardEntry(testUsers[3].nick, 9.0, 4),
                LeaderboardEntry(testUsers[0].nick, 4.6, 5),
                LeaderboardEntry(testUsers[1].nick, 2.5, 6),
                LeaderboardEntry(testUsers[2].nick, 0.0, 0)
        )
        assertEquals(expectedResult, resultList)
    }

    private fun buildGameResultsList(userNick: String, vararg results: Int): List<GameResult> {
        return if (results.isNotEmpty()) results.map { GameResult(userNick, it, 100) } else emptyList()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(localDataRepository)
    }
}
