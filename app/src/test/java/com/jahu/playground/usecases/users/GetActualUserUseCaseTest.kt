package com.jahu.playground.usecases.users

import com.jahu.playground.data.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
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

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetActualUserUseCase(sharedPreferencesManager, dataRepository)
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
        whenever(dataRepository.getUserById(userId)).thenReturn(user)

        val result = useCase.execute()

        assertEquals(user, result)
        verify(sharedPreferencesManager).getActualUserId()
        verify(dataRepository).getUserById(eq(userId))
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataRepository)
    }

}
