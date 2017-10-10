package com.jahu.playground.usecases

import com.jahu.playground.adduser.AddUserContract
import com.jahu.playground.dao.User
import com.jahu.playground.repository.LocalDataRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AddUserUseCaseTest {

    private lateinit var useCase: AddUserUseCase

    @Mock
    private lateinit var dataRepositoryMock: LocalDataRepository

    @Mock
    private lateinit var resultListenerMock: AddUserUseCase.ResultListener

    private val nick = "josh"
    private val user = User("Josh", "Bosh", nick)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = AddUserUseCase(dataRepositoryMock)
    }

    @Test
    fun execute_noUser() {
        whenever(dataRepositoryMock.getUserByNick(any())).thenReturn(null)

        useCase.execute(user, resultListenerMock)

        verify(dataRepositoryMock).getUserByNick(eq(nick))
        verify(dataRepositoryMock).addUser(eq(user))
        verify(resultListenerMock).onSuccess()
    }

    @Test
    fun execute_userExists() {
        whenever(dataRepositoryMock.getUserByNick(any())).thenReturn(mock())

        useCase.execute(user, resultListenerMock)

        verify(dataRepositoryMock).getUserByNick(eq(nick))
        verify(resultListenerMock).onError(AddUserContract.ErrorCode.USER_EXISTS)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataRepositoryMock)
        verifyNoMoreInteractions(resultListenerMock)
    }

}
