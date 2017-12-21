package com.jahu.playground.usecases.users

import com.jahu.playground.data.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.repositories.LocalDataRepository
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UpdateUserUserCaseTest {

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Mock private lateinit var resultListener: UpdateUserUserCase.ResultListener

    private lateinit var useCase: UpdateUserUserCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = UpdateUserUserCase(dataRepository)
    }

    @Test
    fun execute_failure_userDoesNotExist() {
        val id = 1L
        val updatedUser = User(id, "John", "Smith", "nick")
        whenever(dataRepository.getUserById(any())).thenReturn(null)

        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserById(id)
        verify(resultListener).onFailure(EditUserContract.ErrorCode.USER_NOT_EXISTS)
    }

    @Test
    fun execute_failure_nickExists() {
        val nick = "nick"
        val updatedUser = User(1, "John", "Smith", nick)
        whenever(dataRepository.getUserById(any())).thenReturn(mock())
        whenever(dataRepository.getUserByNick(any())).thenReturn(User(2, "Ted", "Honor", nick))

        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserById(1)
        verify(dataRepository).getUserByNick(nick)
        verify(resultListener).onFailure(EditUserContract.ErrorCode.NICK_EXISTS)
    }

    @Test
    fun execute_success_newNick() {
        val nick = "nick"
        val updatedUser = User(1, "John", "Smith", nick)
        whenever(dataRepository.getUserById(any())).thenReturn(mock())
        whenever(dataRepository.getUserByNick(any())).thenReturn(null)

        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserById(1)
        verify(dataRepository).getUserByNick(nick)
        verify(dataRepository).updateUser(updatedUser)
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_success_noNickUpdate() {
        val nick = "nick"
        val updatedUser = User(1, "John", "Smith", nick)
        whenever(dataRepository.getUserById(any())).thenReturn(mock())
        whenever(dataRepository.getUserByNick(any())).thenReturn(User(1, "Tom", "Smith", nick))

        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserById(1)
        verify(dataRepository).getUserByNick(nick)
        verify(dataRepository).updateUser(updatedUser)
        verify(resultListener).onSuccess()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(resultListener)
    }

}
