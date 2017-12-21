package com.jahu.playground.usecases.users

import com.jahu.playground.data.DataSource
import com.jahu.playground.data.SharedPreferencesManager
import com.jahu.playground.data.entities.User
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetActualUserUseCaseTest {

    private lateinit var useCase: GetActualUserUseCase

    @Mock private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Mock private lateinit var dataSource: DataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetActualUserUseCase(sharedPreferencesManager, dataSource)
    }

    @Test
    fun execute_noActualUser() {
        whenever(sharedPreferencesManager.getActualUserId()).thenReturn(-1)

        val result = useCase.execute()

        assertNull(result)
        verify(sharedPreferencesManager).getActualUserId()
    }

    @Test
    fun execute_actualUserFound() {
        val userId = 1L
        val user = User(userId, "Some", "User", "nick")
        whenever(sharedPreferencesManager.getActualUserId()).thenReturn(userId)
        whenever(dataSource.getUserById(userId)).thenReturn(user)

        val result = useCase.execute()

        assertEquals(user, result)
        verify(sharedPreferencesManager).getActualUserId()
        verify(dataSource).getUserById(eq(userId))
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataSource)
    }

}
