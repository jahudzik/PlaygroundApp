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

    @Mock
    private lateinit var sharedPreferencesManagerMock: SharedPreferencesManager

    @Mock
    private lateinit var dataRepositoryMock: LocalDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetActualUserUseCase(sharedPreferencesManagerMock, dataRepositoryMock)
    }

    @Test
    fun execute_noActualUser() {
        whenever(sharedPreferencesManagerMock.getActualUserNick()).thenReturn(null)

        val result = useCase.execute()

        assertNull(result)
        verify(sharedPreferencesManagerMock).getActualUserNick()
    }

    @Test
    fun execute_actualUserFound() {
        val nick = "someNick"
        val user = User("Some", "User", nick)
        whenever(sharedPreferencesManagerMock.getActualUserNick()).thenReturn(nick)
        whenever(dataRepositoryMock.getUserByNick(nick)).thenReturn(user)

        val result = useCase.execute()

        assertEquals(user, result)
        verify(sharedPreferencesManagerMock).getActualUserNick()
        verify(dataRepositoryMock).getUserByNick(eq(nick))
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManagerMock)
        verifyNoMoreInteractions(dataRepositoryMock)
    }

}
