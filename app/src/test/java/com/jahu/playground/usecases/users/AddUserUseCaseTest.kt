package com.jahu.playground.usecases.users

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract
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
    private val firstName = "Josh"
    private val lastName = "Bosh"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = AddUserUseCase(dataRepository)
    }

    @Test
    fun execute_noUsers() {
        whenever(dataRepository.getUserByNick(any())).thenReturn(null)
        whenever(dataRepository.getHighestUserId()).thenReturn(null)

        useCase.execute(firstName, lastName, nick, resultListener)

        verify(dataRepository).getUserByNick(eq(nick))
        verify(dataRepository).getHighestUserId()
        verify(dataRepository).addUser(eq(User(1, firstName, lastName, nick)))
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_noSuchUser() {
        whenever(dataRepository.getUserByNick(any())).thenReturn(null)
        whenever(dataRepository.getHighestUserId()).thenReturn(1)

        useCase.execute(firstName, lastName, nick, resultListener)

        verify(dataRepository).getUserByNick(eq(nick))
        verify(dataRepository).getHighestUserId()
        verify(dataRepository).addUser(eq(User(2, firstName, lastName, nick)))
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_suchUserExists() {
        whenever(dataRepository.getUserByNick(any())).thenReturn(mock())
        whenever(dataRepository.getHighestUserId()).thenReturn(1)

        useCase.execute(firstName, lastName, nick, resultListener)

        verify(dataRepository).getUserByNick(eq(nick))
        verify(resultListener).onFailure(EditUserContract.ErrorCode.USER_EXISTS)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(resultListener)
    }

}
