package com.jahu.playground.features.settings

import com.jahu.playground.features.edituser.EditUserContract
import com.jahu.playground.usecases.users.SetActualUserUseCase
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SettingsPresenterTest {

    private lateinit var presenter: SettingsContract.Presenter

    @Mock private lateinit var view: SettingsContract.View

    @Mock private lateinit var setActualUserUseCase: SetActualUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SettingsPresenter(view, setActualUserUseCase)
    }

    @Test
    fun onEditUserButtonClicked_expected() {
        presenter.onEditUserButtonClicked()

        verify(view).navigateToEditUserScreen(EditUserContract.Mode.EDIT_USER)
    }

    @Test
    fun onLogoutButtonClicked_expected() {
        presenter.onLogoutButtonClicked()

        verify(setActualUserUseCase).execute(null)
        verify(view).logout()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(setActualUserUseCase)
    }

}
