package com.jahu.playground.features.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.usecases.GetActualUserUseCase
import com.jahu.playground.usecases.GetUsersUseCase
import com.jahu.playground.usecases.SetActualUserUseCase
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
    private lateinit var getActualUserUseCaseMock: GetActualUserUseCase

    @Mock
    private lateinit var getUsersUseCaseMock: GetUsersUseCase

    @Mock
    private lateinit var setActualUserUseCase: SetActualUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ChooseUserPresenter(viewMock, getActualUserUseCaseMock, getUsersUseCaseMock, setActualUserUseCase)
    }

    @Test
    fun resumeView_userAlreadyChosen() {
        whenever(getActualUserUseCaseMock.execute()).thenReturn(mock())

        presenter.resumeView()

        verify(getActualUserUseCaseMock).execute()
        verify(viewMock).navigateToApp()
    }

    @Test
    fun resumeView_noUsers() {
        whenever(getActualUserUseCaseMock.execute()).thenReturn(null)
        whenever(getUsersUseCaseMock.execute()).thenReturn(emptySet())

        presenter.resumeView()

        verify(getActualUserUseCaseMock).execute()
        verify(getUsersUseCaseMock).execute()
        verify(viewMock).showNoUsersMessage()
    }

    @Test
    fun resumeView_someUsers() {
        val user1 = User("user1", "Amy", "Adam")
        val user2 = User("user2", "Eva", "Black")
        val user3 = User("user3", "Mike", "Close")
        val usersSet = setOf(user3, user1, user2)
        val expectedUsersList = listOf(user1, user2, user3)
        whenever(getActualUserUseCaseMock.execute()).thenReturn(null)
        whenever(getUsersUseCaseMock.execute()).thenReturn(usersSet)

        presenter.resumeView()

        verify(getActualUserUseCaseMock).execute()
        verify(getUsersUseCaseMock).execute()
        verify(viewMock).showUsersList(eq(expectedUsersList))
    }

    @Test
    fun onUserChosen_expected() {
        val nick = "test"

        presenter.onUserChosen(User("First", "Last", nick))

        verify(setActualUserUseCase).execute(eq(nick))
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
        verifyNoMoreInteractions(getActualUserUseCaseMock)
        verifyNoMoreInteractions(getUsersUseCaseMock)
        verifyNoMoreInteractions(setActualUserUseCase)
    }

}
