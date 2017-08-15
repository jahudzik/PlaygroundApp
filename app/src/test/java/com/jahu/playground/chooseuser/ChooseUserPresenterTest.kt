package com.jahu.playground.chooseuser

import com.jahu.playground.data.User
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ChooseUserPresenterTest {

    lateinit var presenter: ChooseUserPresenter

    @Mock
    lateinit var viewMock: ChooseUserContract.View

    @Mock
    lateinit var addUserUseCaseMock: AddUserUseCase

    @Mock
    lateinit var getUsersUseCaseMock: GetUsersUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ChooseUserPresenter(viewMock, getUsersUseCaseMock, addUserUseCaseMock)
    }

    @Test
    fun initView_noUsers() {
        whenever(getUsersUseCaseMock.execute()).thenReturn(emptyList())

        presenter.initView()

        verify(getUsersUseCaseMock).execute()
        verify(viewMock).showNoUsersMessage()
        verify(viewMock).showNewUserInput()
    }

    @Test
    fun initView_someUsers() {
        val usersList = listOf(mock<User>(), mock<User>(), mock<User>())
        whenever(getUsersUseCaseMock.execute()).thenReturn(usersList)

        presenter.initView()

        verify(getUsersUseCaseMock).execute()
        verify(viewMock).showUsersList(eq(usersList))
        verify(viewMock).showNewUserInput()
    }

    @Test
    fun onExistingUserChosen() {
        presenter.onExistingUserChosen(mock<User>())

        verify(viewMock).navigateToApp()
    }

    @Test
    fun onNewUserInputClicked() {
        presenter.onNewUserInputClicked()

        verify(viewMock).makeNewUserInputEditable()
    }

    @Test
    fun onNewUserNameEntered_emptyValue() {
        presenter.onNewUserNameEntered("")

        verify(viewMock).showError(ChooseUserContract.Error.EMPTY_NAME)
    }

    @Test
    fun onNewUserNameEntered_existingValue() {
        val someName = "John Smith"
        whenever(getUsersUseCaseMock.execute()).thenReturn(createUsersList("Anna Jones", someName, "Andy McDonald"))

        presenter.onNewUserNameEntered(someName)

        verify(getUsersUseCaseMock).execute()
        verify(viewMock).showError(ChooseUserContract.Error.EXISTING_NAME)
    }

    @Test
    fun onNewUserNameEntered_correctValue() {
        val someName = "Julia Truman"
        whenever(getUsersUseCaseMock.execute()).thenReturn(createUsersList("Anna Jones", "John Smith", "Andy McDonald"))

        presenter.onNewUserNameEntered(someName)

        verify(getUsersUseCaseMock).execute()
        verify(addUserUseCaseMock).execute(eq(someName))
        verify(viewMock).navigateToApp()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(viewMock)
        verifyNoMoreInteractions(addUserUseCaseMock)
        verifyNoMoreInteractions(getUsersUseCaseMock)
    }

    private fun createUsersList(vararg names: String): List<User> {
        var id: Int = 1
        return names.map { name -> User(id++, name) }
    }
}