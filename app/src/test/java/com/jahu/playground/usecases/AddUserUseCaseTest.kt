package com.jahu.playground.usecases

import com.jahu.playground.dao.User
import com.jahu.playground.features.adduser.AddUserContract
import com.jahu.playground.repositories.LocalDataRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AddUserUseCaseTest {

    private lateinit var useCase: AddUserUseCase

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Mock private lateinit var resultListener: AddUserUseCase.ResultListener

    private val nick = "josh"
    private val user = User("Josh", "Bosh", nick)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = AddUserUseCase(dataRepository)
    }

    @Test
    fun execute_noUser() {
        whenever(dataRepository.getUserByNick(any())).thenReturn(null)

        useCase.execute(user, resultListener)

        verify(dataRepository).getUserByNick(eq(nick))
        verify(dataRepository).addUser(eq(user))
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_userExists() {
        whenever(dataRepository.getUserByNick(any())).thenReturn(mock())

        useCase.execute(user, resultListener)

        verify(dataRepository).getUserByNick(eq(nick))
        verify(resultListener).onError(AddUserContract.ErrorCode.USER_EXISTS)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(resultListener)
    }

}
