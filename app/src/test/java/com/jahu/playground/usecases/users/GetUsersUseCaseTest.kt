package com.jahu.playground.usecases.users

import com.jahu.playground.data.DataSource
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetUsersUseCaseTest {

    private lateinit var useCase: GetUsersUseCase

    @Mock private lateinit var dataSource: DataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetUsersUseCase(dataSource)
    }

    @Test
    fun execute_expected() {
        useCase.execute()

        verify(dataSource).getAllUsers()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataSource)
    }
}
