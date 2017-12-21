package com.jahu.playground.usecases.users

import com.jahu.playground.data.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.repositories.DataSource
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AddUserUseCaseTest {

    private lateinit var useCase: AddUserUseCase

    @Mock private lateinit var dataSource: DataSource

    @Mock private lateinit var resultListener: AddUserUseCase.ResultListener

    private val nick = "josh"
    private val firstName = "Josh"
    private val lastName = "Bosh"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = AddUserUseCase(dataSource)
    }

    @Test
    fun execute_noUsers() {
        whenever(dataSource.getUserByNick(any())).thenReturn(null)
        whenever(dataSource.getHighestUserId()).thenReturn(null)

        useCase.execute(firstName, lastName, nick, resultListener)

        verify(dataSource).getUserByNick(eq(nick))
        verify(dataSource).getHighestUserId()
        verify(dataSource).addUser(eq(User(1, firstName, lastName, nick)))
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_noSuchUser() {
        whenever(dataSource.getUserByNick(any())).thenReturn(null)
        whenever(dataSource.getHighestUserId()).thenReturn(1)

        useCase.execute(firstName, lastName, nick, resultListener)

        verify(dataSource).getUserByNick(eq(nick))
        verify(dataSource).getHighestUserId()
        verify(dataSource).addUser(eq(User(2, firstName, lastName, nick)))
        verify(resultListener).onSuccess()
    }

    @Test
    fun execute_suchNickExists() {
        whenever(dataSource.getUserByNick(any())).thenReturn(mock())
        whenever(dataSource.getHighestUserId()).thenReturn(1)

        useCase.execute(firstName, lastName, nick, resultListener)

        verify(dataSource).getUserByNick(eq(nick))
        verify(resultListener).onFailure(EditUserContract.ErrorCode.NICK_EXISTS)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(dataSource)
        verifyNoMoreInteractions(resultListener)
    }

}
