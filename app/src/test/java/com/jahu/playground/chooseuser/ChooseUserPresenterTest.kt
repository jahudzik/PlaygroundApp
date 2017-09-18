package com.jahu.playground.chooseuser

import com.jahu.playground.dao.User
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ChooseUserPresenterTest {

    private lateinit var presenter: ChooseUserPresenter

    @Mock
    private lateinit var viewMock: ChooseUserContract.View

    @Mock
    private lateinit var getUsersUseCaseMock: GetUsersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ChooseUserPresenter(viewMock, getUsersUseCaseMock)
    }

    @Test
    fun initView_noUsers() {
        whenever(getUsersUseCaseMock.execute()).thenReturn(emptyList())

        presenter.initView()

        verify(getUsersUseCaseMock).execute()
        verify(viewMock).showNoUsersMessage()
    }

    @Test
    fun initView_someUsers() {
        val usersList = listOf(mock(), mock(), mock<User>())
        whenever(getUsersUseCaseMock.execute()).thenReturn(usersList)

        presenter.initView()

        verify(getUsersUseCaseMock).execute()
        verify(viewMock).showUsersList(eq(usersList))
    }

    @Test
    fun onUserChosen_expected() {
        presenter.onUserChosen(mock())

        verify(viewMock).navigateToApp()
    }

    @Test
    fun onAddUserButtonClicked_expected() {
        presenter.onAddUserButtonClicked()

        verify(viewMock).navigateToAddUserScreen()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(viewMock)
        verifyNoMoreInteractions(getUsersUseCaseMock)
    }

}