package com.jahu.playground.usecases

import com.jahu.playground.dao.User
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
        whenever(sharedPreferencesManager.getActualUserNick()).thenReturn(null)

        val result = useCase.execute()

        assertNull(result)
        verify(sharedPreferencesManager).getActualUserNick()
    }

    @Test
    fun execute_actualUserFound() {
        val nick = "someNick"
        val user = User("Some", "User", nick)
        whenever(sharedPreferencesManager.getActualUserNick()).thenReturn(nick)
        whenever(dataRepository.getUserByNick(nick)).thenReturn(user)

        val result = useCase.execute()

        assertEquals(user, result)
        verify(sharedPreferencesManager).getActualUserNick()
        verify(dataRepository).getUserByNick(eq(nick))
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataRepository)
    }

}
