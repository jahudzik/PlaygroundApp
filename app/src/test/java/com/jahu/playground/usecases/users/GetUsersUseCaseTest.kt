package com.jahu.playground.usecases.users

import com.jahu.playground.repositories.LocalDataRepository
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetUsersUseCaseTest {

    private lateinit var useCase: GetUsersUseCase

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetUsersUseCase(dataRepository)
    }

    @Test
    fun execute_expected() {
        useCase.execute()

        verify(dataRepository).getAllUsers()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataRepository)
    }
}
