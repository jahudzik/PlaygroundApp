package com.jahu.playground.features.chooseuser

import com.jahu.playground.dao.User
import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.usecases.users.GetActualUserUseCase
import com.jahu.playground.usecases.users.GetUsersUseCase
import com.jahu.playground.usecases.users.SetActualUserUseCase
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ChooseUserPresenterTest {

    private lateinit var presenter: ChooseUserPresenter

    @Mock private lateinit var view: ChooseUserContract.View

    @Mock private lateinit var getActualUserUseCase: GetActualUserUseCase

    @Mock private lateinit var getUsersUseCase: GetUsersUseCase

    @Mock private lateinit var setActualUserUseCase: SetActualUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ChooseUserPresenter(view, getActualUserUseCase, getUsersUseCase, setActualUserUseCase)
    }

    @Test
    fun resumeView_userAlreadyChosen() {
        whenever(getActualUserUseCase.execute()).thenReturn(mock())

        presenter.resumeView()

        verify(getActualUserUseCase).execute()
        verify(view).navigateToApp()
    }

    @Test
    fun resumeView_noUsers() {
        whenever(getActualUserUseCase.execute()).thenReturn(null)
        whenever(getUsersUseCase.execute()).thenReturn(emptySet())

        presenter.resumeView()

        verify(getActualUserUseCase).execute()
        verify(getUsersUseCase).execute()
        verify(view).showNoUsersMessage()
    }

    @Test
    fun resumeView_someUsers() {
        val user1 = User(1, "user1", "Amy", "Adam")
        val user2 = User(2, "user2", "Eva", "Black")
        val user3 = User(3, "user3", "Mike", "Close")
        val usersSet = setOf(user3, user1, user2)
        val expectedUsersList = listOf(user1, user2, user3)
        whenever(getActualUserUseCase.execute()).thenReturn(null)
        whenever(getUsersUseCase.execute()).thenReturn(usersSet)

        presenter.resumeView()

        verify(getActualUserUseCase).execute()
        verify(getUsersUseCase).execute()
        verify(view).showUsersList(eq(expectedUsersList))
    }

    @Test
    fun onUserChosen_expected() {
        val nick = "test"

        presenter.onUserChosen(User(1, "First", "Last", nick))

        verify(setActualUserUseCase).execute(eq(nick))
        verify(view).navigateToApp()
    }

    @Test
    fun onAddUserButtonClicked_expected() {
        presenter.onAddUserButtonClicked()

        verify(view).navigateToEditUserScreen(EditUserContract.Mode.ADD_USER)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(getActualUserUseCase)
        verifyNoMoreInteractions(getUsersUseCase)
        verifyNoMoreInteractions(setActualUserUseCase)
    }

}
