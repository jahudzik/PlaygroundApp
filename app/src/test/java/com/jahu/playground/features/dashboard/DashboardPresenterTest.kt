package com.jahu.playground.features.dashboard

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DashboardPresenterTest {

    private lateinit var presenter: DashboardPresenter

    @Mock private lateinit var view: DashboardContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DashboardPresenter(view)
    }


    @Test
    fun resumeView_expected() {
        presenter.resumeView()

        verify(view).showQuizSetupScreen()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
    }
}
