package com.jahu.playground.usecases.games

import com.jahu.playground.data.GameResult
import com.jahu.playground.data.LeaderboardEntry
import com.jahu.playground.data.User
import com.jahu.playground.repositories.DataSource
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

    @Mock private lateinit var dataSource: DataSource

    private val testUsers = listOf(
            User(1, "John", "Smith", "smithy"),
            User(2, "Anna", "Brown", "anna"),
            User(3, "Eric", "Newman", "eric"),
            User(4, "Maria", "McDonald", "mary")
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetLeaderboardEntriesUseCase(dataSource)
    }

    @Test
    fun execute_singleUser_coupleGamesPlayed() {
        val userGames = buildGameResultsList(testUsers[0].id, 8, 6, 7, 4)
        whenever(dataSource.getAllUsers()).thenReturn(setOf(testUsers[0]))
        whenever(dataSource.getGameResults()).thenReturn(userGames)

        val resultList = useCase.execute()

        verify(dataSource).getGameResults()
        verify(dataSource).getAllUsers()
        val expectedResult = listOf(LeaderboardEntry(testUsers[0].nick, 6.25, 4))
        assertEquals(expectedResult, resultList)
    }

    @Test
    fun execute_roundAverageScore() {
        val userGames = buildGameResultsList(testUsers[0].id, 2, 2, 2, 2, 2, 3)
        whenever(dataSource.getAllUsers()).thenReturn(setOf(testUsers[0]))
        whenever(dataSource.getGameResults()).thenReturn(userGames)

        val resultList = useCase.execute()

        verify(dataSource).getGameResults()
        verify(dataSource).getAllUsers()
        val expectedResult = listOf(LeaderboardEntry(testUsers[0].nick, 2.17, 6))
        assertEquals(expectedResult, resultList)
    }

    @Test
    fun execute_singleUser_noGamesPlayed() {
        whenever(dataSource.getAllUsers()).thenReturn(setOf(testUsers[0]))
        whenever(dataSource.getGameResults()).thenReturn(emptyList())

        val resultList = useCase.execute()

        verify(dataSource).getGameResults()
        verify(dataSource).getAllUsers()
        val expectedResult = listOf(LeaderboardEntry(testUsers[0].nick, 0.0, 0))
        assertEquals(expectedResult, resultList)
    }

    @Test
    fun execute_multipleUsers() {
        val userGames = buildGameResultsList(testUsers[0].id, 7, 6, 1, 4, 5)
                .plus(buildGameResultsList(testUsers[1].id, 1, 4, 5, 2, 1, 2))
                .plus(buildGameResultsList(testUsers[2].id))
                .plus(buildGameResultsList(testUsers[3].id, 10, 9, 8, 9))
        whenever(dataSource.getAllUsers()).thenReturn(testUsers.toSet())
        whenever(dataSource.getGameResults()).thenReturn(userGames)

        val resultList = useCase.execute()

        verify(dataSource).getGameResults()
        verify(dataSource).getAllUsers()
        val expectedResult = listOf(
                LeaderboardEntry(testUsers[3].nick, 9.0, 4),
                LeaderboardEntry(testUsers[0].nick, 4.6, 5),
                LeaderboardEntry(testUsers[1].nick, 2.5, 6),
                LeaderboardEntry(testUsers[2].nick, 0.0, 0)
        )
        assertEquals(expectedResult, resultList)
    }

    private fun buildGameResultsList(userId: Long, vararg results: Int): List<GameResult> {
        return if (results.isNotEmpty()) results.map { GameResult(userId, it, 100) } else emptyList()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataSource)
    }
}
