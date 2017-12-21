package com.jahu.playground.usecases.games

import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.repositories.DataSource
import com.jahu.playground.repositories.SharedPreferencesManager
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AddGameResultUseCaseTest {

    private lateinit var useCase: AddGameResultUseCase

    @Mock private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Mock private lateinit var dataSource: DataSource

    @Mock private lateinit var timeProvider: TimeProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = AddGameResultUseCase(sharedPreferencesManager, dataSource, timeProvider)
    }

    @Test
    fun execute_expected() {
        val userId = 1L
        val correctAnswersCount = 9
        val currentTimestamp = 777L
        whenever(sharedPreferencesManager.getActualUserId()).thenReturn(userId)
        whenever(timeProvider.getCurrentTimestamp()).thenReturn(currentTimestamp)

        useCase.execute(correctAnswersCount)

        verify(sharedPreferencesManager).getActualUserId()
        verify(timeProvider).getCurrentTimestamp()
        verify(dataSource).addGameResult(com.nhaarman.mockito_kotlin.check {
            assertEquals(userId, it.userId)
            assertEquals(correctAnswersCount, it.correctAnswersCount)
            assertEquals(currentTimestamp, it.timestamp)
        })
    }

    @Test
    fun execute_nullNick() {
        whenever(sharedPreferencesManager.getActualUserId()).thenReturn(-1L)

        useCase.execute(4)

        verify(sharedPreferencesManager).getActualUserId()
        // nothing else happens
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataSource)
        verifyNoMoreInteractions(timeProvider)
    }
}
