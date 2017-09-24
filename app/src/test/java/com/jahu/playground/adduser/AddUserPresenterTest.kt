package com.jahu.playground.adduser

import com.jahu.playground.dao.User
import com.jahu.playground.usecases.AddUserUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AddUserPresenterTest {

    private lateinit var presenter: AddUserPresenter

    @Mock
    private lateinit var viewMock: AddUserContract.View

    @Mock
    private lateinit var addUserUseCaseMock: AddUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = AddUserPresenter(viewMock, addUserUseCaseMock)
    }

    @Test
    fun initView_expected() {
        presenter.initView()

        verify(viewMock).setAddButtonEnabled(false)
    }

    @Test
    fun onFieldValueChanged_noEmptyValues() {
        presenter.onFieldValueChanged("John", "Smith", "j")

        verify(viewMock).setAddButtonEnabled(true)
    }

    @Test
    fun onFieldValueChanged_firstNameEmpty() {
        presenter.onFieldValueChanged("", "Smith", "johnny")

        verify(viewMock).setAddButtonEnabled(false)
    }

    @Test
    fun onFieldValueChanged_lastNameEmpty() {
        presenter.onFieldValueChanged("John", "", "johnny")

        verify(viewMock).setAddButtonEnabled(false)
    }

    @Test
    fun onFieldValueChanged_nickEmpty() {
        presenter.onFieldValueChanged("John", "Smith", "")

        verify(viewMock).setAddButtonEnabled(false)
    }

    @Test
    fun onFieldValueChanged_allValuesEmpty() {
        presenter.onFieldValueChanged("", "", "")

        verify(viewMock).setAddButtonEnabled(false)
    }

    @Test
    fun onAddButtonClicked_expected() {
        val firstName = "John"
        val lastName = "Smith"
        val nick = "js"

        presenter.onAddButtonClicked(firstName, lastName, nick)

        verify(addUserUseCaseMock).execute(eq(User(firstName, lastName, nick)), any())
    }

    @Test
    fun onSuccess_expected() {
        presenter.onSuccess()

        verify(viewMock).close()
    }

    @Test
    fun onError_expected() {
        val errorCode = AddUserContract.ErrorCode.USER_EXISTS

        presenter.onError(errorCode)

        verify(viewMock).showErrorMessage(errorCode)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(viewMock)
        verifyNoMoreInteractions(addUserUseCaseMock)
    }
}