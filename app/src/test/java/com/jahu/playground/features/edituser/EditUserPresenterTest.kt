package com.jahu.playground.features.edituser

import com.jahu.playground.data.entities.User
import com.jahu.playground.usecases.users.AddUserUseCase
import com.jahu.playground.usecases.users.GetActualUserUseCase
import com.jahu.playground.usecases.users.UpdateUserUserCase
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class EditUserPresenterTest {

    private lateinit var presenter: EditUserPresenter

    @Mock private lateinit var view: EditUserContract.View

    @Mock private lateinit var addUserUseCase: AddUserUseCase

    @Mock private lateinit var getActualUserUseCase: GetActualUserUseCase

    @Mock private lateinit var updateUserUseCase: UpdateUserUserCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EditUserPresenter(EditUserContract.Mode.ADD_USER, view, addUserUseCase, getActualUserUseCase, updateUserUseCase)
    }

    @Test
    fun resumeView_addMode() {
        initAddMode()

        presenter.resumeView()

        verify(view).setConfirmButtonEnabled(false)
        verify(view).setAddButtonLabel()
    }

    @Test
    fun resumeView_editMode() {
        val user = mock<User>()
        whenever(getActualUserUseCase.execute()).thenReturn(user)
        initEditMode()

        presenter.resumeView()

        verify(getActualUserUseCase).execute()
        verify(view).fillUserData(user)
        verify(view).setConfirmButtonEnabled(true)
        verify(view).setSaveButtonLabel()
    }

    @Test
    fun onFieldValueChanged_noEmptyValues() {
        presenter.onFieldValueChanged("John", "Smith", "j")

        verify(view).setConfirmButtonEnabled(true)
    }

    @Test
    fun onFieldValueChanged_firstNameEmpty() {
        presenter.onFieldValueChanged("", "Smith", "johnny")

        verify(view).setConfirmButtonEnabled(false)
    }

    @Test
    fun onFieldValueChanged_lastNameEmpty() {
        presenter.onFieldValueChanged("John", "", "johnny")

        verify(view).setConfirmButtonEnabled(false)
    }

    @Test
    fun onFieldValueChanged_nickEmpty() {
        presenter.onFieldValueChanged("John", "Smith", "")

        verify(view).setConfirmButtonEnabled(false)
    }

    @Test
    fun onFieldValueChanged_allValuesEmpty() {
        presenter.onFieldValueChanged("", "", "")

        verify(view).setConfirmButtonEnabled(false)
    }

    @Test
    fun onConfirmButtonClicked_addMode_success() {
        val firstName = "John"
        val lastName = "Smith"
        val nick = "js"
        whenever(addUserUseCase.execute(any(), any(), any(), any())).thenAnswer { invocation ->
            val resultListener = invocation.getArgument<AddUserUseCase.ResultListener>(3)
            resultListener.onSuccess()
        }
        initAddMode()

        presenter.onConfirmButtonClicked(firstName, lastName, nick)

        verify(addUserUseCase).execute(eq(firstName), eq(lastName), eq(nick), any())
        verify(view).close()
    }

    @Test
    fun onConfirmButtonClicked_addMode_failure() {
        val firstName = "John"
        val lastName = "Smith"
        val nick = "js"
        whenever(addUserUseCase.execute(any(), any(), any(), any())).thenAnswer { invocation ->
            val resultListener = invocation.getArgument<AddUserUseCase.ResultListener>(3)
            resultListener.onFailure(EditUserContract.ErrorCode.NICK_EXISTS)
        }
        initAddMode()

        presenter.onConfirmButtonClicked(firstName, lastName, nick)

        verify(addUserUseCase).execute(eq(firstName), eq(lastName), eq(nick), any())
        verify(view).showErrorMessage(EditUserContract.ErrorCode.NICK_EXISTS)
    }

    @Test
    fun onConfirmButtonClicked_editMode_success() {
        val id = 3L
        val firstName = "John"
        val lastName = "Smith"
        val nick = "js"
        val actualUser = User(id, "Johnny", "Jones", "js")
        whenever(getActualUserUseCase.execute()).thenReturn(actualUser)
        whenever(updateUserUseCase.execute(any(), any())).thenAnswer { invocation ->
            val resultListener = invocation.getArgument<UpdateUserUserCase.ResultListener>(1)
            resultListener.onSuccess()
        }
        initEditMode()

        presenter.onConfirmButtonClicked(firstName, lastName, nick)

        verify(getActualUserUseCase).execute()
        verify(updateUserUseCase).execute(eq(User(id, firstName, lastName, nick)), any())
        verify(view).close()
    }

    @Test
    fun onConfirmButtonClicked_editMode_failure() {
        val id = 3L
        val firstName = "John"
        val lastName = "Smith"
        val nick = "js"
        val actualUser = User(id, "Johnny", "Jones", "js")
        whenever(getActualUserUseCase.execute()).thenReturn(actualUser)
        whenever(updateUserUseCase.execute(any(), any())).thenAnswer { invocation ->
            val resultListener = invocation.getArgument<UpdateUserUserCase.ResultListener>(1)
            resultListener.onFailure(EditUserContract.ErrorCode.USER_NOT_EXISTS)
        }
        initEditMode()

        presenter.onConfirmButtonClicked(firstName, lastName, nick)

        verify(getActualUserUseCase).execute()
        verify(updateUserUseCase).execute(eq(User(id, firstName, lastName, nick)), any())
        verify(view).showErrorMessage(EditUserContract.ErrorCode.USER_NOT_EXISTS)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(addUserUseCase)
        verifyNoMoreInteractions(getActualUserUseCase)
        verifyNoMoreInteractions(updateUserUseCase)
    }

    private fun initAddMode() {
        presenter = EditUserPresenter(EditUserContract.Mode.ADD_USER, view, addUserUseCase, getActualUserUseCase, updateUserUseCase)
    }

    private fun initEditMode() {
        presenter = EditUserPresenter(EditUserContract.Mode.EDIT_USER, view, addUserUseCase, getActualUserUseCase, updateUserUseCase)
    }
}
