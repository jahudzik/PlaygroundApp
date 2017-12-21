package com.jahu.playground.usecases.users

import com.jahu.playground.data.SharedPreferencesManager
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SetActualUserUseCaseTest {

    private lateinit var useCase: SetActualUserUseCase

    @Mock private lateinit var preferencesManager: SharedPreferencesManager

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = SetActualUserUseCase(preferencesManager)
    }

    @Test
    fun execute_expected() {
        useCase.execute(14)

        verify(preferencesManager).setActualUserId(14)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(preferencesManager)
    }
}
