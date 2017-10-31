package com.jahu.playground.features.edituser

import com.jahu.playground.dao.User
import com.jahu.playground.usecases.users.AddUserUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class EditUserPresenterTest {

    private lateinit var presenter: EditUserPresenter

    @Mock private lateinit var view: EditUserContract.View

    @Mock private lateinit var addUserUseCase: AddUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EditUserPresenter(EditUserContract.Mode.ADD_USER, view, addUserUseCase)
    }

    @Test
    fun resumeView_addMode() {
        presenter = EditUserPresenter(EditUserContract.Mode.ADD_USER, view, addUserUseCase)

        presenter.resumeView()

        verify(view).setConfirmButtonEnabled(false)
        verify(view).setAddButtonLabel()
    }

    @Test
    fun resumeView_editMode() {
        presenter = EditUserPresenter(EditUserContract.Mode.EDIT_USER, view, addUserUseCase)

        presenter.resumeView()

        verify(view).setConfirmButtonEnabled(false)
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
    fun onConfirmButtonClicked_expected() {
        val firstName = "John"
        val lastName = "Smith"
        val nick = "js"

        presenter.onConfirmButtonClicked(firstName, lastName, nick)

        verify(addUserUseCase).execute(eq(User(firstName, lastName, nick)), any())
    }

    @Test
    fun onSuccess_expected() {
        presenter.onSuccess()

        verify(view).close()
    }

    @Test
    fun onError_expected() {
        val errorCode = EditUserContract.ErrorCode.USER_EXISTS

        presenter.onFailure(errorCode)

        verify(view).showErrorMessage(errorCode)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(addUserUseCase)
    }
}
