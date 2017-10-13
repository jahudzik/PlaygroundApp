package com.jahu.playground.features.quizsetup

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class QuizSetupPresenterTest {

    private lateinit var presenter: QuizSetupPresenter

    @Mock private lateinit var view: QuizSetupContract.View

    @Mock private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = QuizSetupPresenter(view, sharedPreferencesManager, dataRepository)
    }

    @Test
    fun resumeView_noUserNick() {
        whenever(sharedPreferencesManager.getActualUserNick()).thenReturn(null)

        var exceptionThrown = false
        try {
            presenter.resumeView()
        } catch (e: IllegalStateException) {
            exceptionThrown = true
            verify(sharedPreferencesManager).getActualUserNick()
        }
        assertTrue(exceptionThrown)
    }

    @Test
    fun resumeView_noUserData() {
        val nick = "someNick"
        whenever(sharedPreferencesManager.getActualUserNick()).thenReturn(nick)
        whenever(dataRepository.getUserByNick(nick)).thenReturn(null)

        var exceptionThrown = false
        try {
            presenter.resumeView()
        } catch (e: IllegalStateException) {
            exceptionThrown = true
            verify(sharedPreferencesManager).getActualUserNick()
            verify(dataRepository).getUserByNick(eq(nick))
        }
        assertTrue(exceptionThrown)
    }

    @Test
    fun resumeView_expected() {
        val nick = "someNick"
        val user = User("John", "Smith", nick)
        whenever(sharedPreferencesManager.getActualUserNick()).thenReturn(nick)
        whenever(dataRepository.getUserByNick(nick)).thenReturn(user)

        presenter.resumeView()

        verify(sharedPreferencesManager).getActualUserNick()
        verify(dataRepository).getUserByNick(eq(nick))
        verify(view).showUserName(eq(user.firstName))
    }

    @Test
    fun onStartQuizButtonClicked_expected() {
        presenter.onStartQuizButtonClicked()

        verify(view).showNewQuizScreen()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(view)
    }

}
