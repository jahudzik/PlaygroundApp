package com.jahu.playground.usecases.users

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.repositories.LocalDataRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
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
    fun execute_noNickUpdate() {
        val nick = "nick"
        val updatedUser = User(1, "John", "Smith", nick)
        whenever(dataRepository.getUserByNick(any())).thenReturn(User(1, "Tom", "Smith", nick))
        whenever(dataRepository.updateUser(any())).thenReturn(LocalDataRepository.OperationResult.SUCCESS)

        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserByNick(nick)
        verify(dataRepository).updateUser(updatedUser)
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_newNick() {
        val nick = "nick"
        val updatedUser = User(1, "John", "Smith", nick)
        whenever(dataRepository.getUserByNick(any())).thenReturn(null)
        whenever(dataRepository.updateUser(any())).thenReturn(LocalDataRepository.OperationResult.SUCCESS)
        
        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserByNick(nick)
        verify(dataRepository).updateUser(updatedUser)
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_nickExists() {
        val nick = "nick"
        val updatedUser = User(1, "John", "Smith", nick)
        whenever(dataRepository.getUserByNick(any())).thenReturn(User(2, "Ted", "Honor", nick))

        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserByNick(nick)
        verify(resultListener).onFailure(EditUserContract.ErrorCode.NICK_EXISTS)
    }

    @Test
    fun execute_userDoesNotExist() {
        val nick = "nick"
        val updatedUser = User(1, "John", "Smith", nick)
        whenever(dataRepository.getUserByNick(any())).thenReturn(null)
        whenever(dataRepository.updateUser(any())).thenReturn(LocalDataRepository.OperationResult.FAILURE_USER_NOT_EXISTS)

        useCase.execute(updatedUser, resultListener)

        verify(dataRepository).getUserByNick(nick)
        verify(dataRepository).updateUser(updatedUser)
        verify(resultListener).onFailure(EditUserContract.ErrorCode.USER_NOT_EXISTS)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(resultListener)
    }

}
