package com.jahu.playground.usecases

import com.jahu.playground.repositories.SharedPreferencesManager
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SetActualUserUseCaseTest {

    private lateinit var useCase: SetActualUserUseCase

    @Mock
    private lateinit var preferencesManagerMock: SharedPreferencesManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = SetActualUserUseCase(preferencesManagerMock)
    }

    @Test
    fun execute_expected() {
        val nick = "jahu"
        useCase.execute(nick)

        verify(preferencesManagerMock).setActualUserNick(nick)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(preferencesManagerMock)
    }
}
