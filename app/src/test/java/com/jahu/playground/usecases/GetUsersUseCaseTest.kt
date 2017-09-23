package com.jahu.playground.usecases

import com.jahu.playground.repository.LocalDataRepository
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetUsersUseCaseTest {

    private lateinit var useCase: GetUsersUseCase

    @Mock
    private lateinit var dataRepositoryMock: LocalDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetUsersUseCase(dataRepositoryMock)
    }

    @Test
    fun execute_expected() {
        useCase.execute()

        verify(dataRepositoryMock).getAllUsers()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataRepositoryMock)
    }
}