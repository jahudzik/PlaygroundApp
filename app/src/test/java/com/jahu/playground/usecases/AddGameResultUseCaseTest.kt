package com.jahu.playground.usecases

import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.repositories.LocalDataRepository
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

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Mock private lateinit var timeProvider: TimeProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = AddGameResultUseCase(sharedPreferencesManager, dataRepository, timeProvider)
    }

    @Test
    fun execute_expected() {
        val nick = "someNick"
        val correctAnswersCount = 9
        val currentTimestamp = 777L
        whenever(sharedPreferencesManager.getActualUserNick()).thenReturn(nick)
        whenever(timeProvider.getCurrentTimestamp()).thenReturn(currentTimestamp)

        useCase.execute(correctAnswersCount)

        verify(sharedPreferencesManager).getActualUserNick()
        verify(timeProvider).getCurrentTimestamp()
        verify(dataRepository).addGameResult(com.nhaarman.mockito_kotlin.check {
            assertEquals(nick, it.nick)
            assertEquals(correctAnswersCount, it.correctAnswersCount)
            assertEquals(currentTimestamp, it.timestamp)
        })
    }

    @Test
    fun execute_nullNick() {
        whenever(sharedPreferencesManager.getActualUserNick()).thenReturn(null)

        useCase.execute(4)

        verify(sharedPreferencesManager).getActualUserNick()
        // nothing else happens
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(timeProvider)
    }
}
