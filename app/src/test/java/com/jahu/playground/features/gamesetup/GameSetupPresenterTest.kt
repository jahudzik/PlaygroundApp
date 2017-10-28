package com.jahu.playground.features.gamesetup

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.GetNewQuestionsUseCase
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GameSetupPresenterTest {

    private lateinit var presenter: GameSetupPresenter

    @Mock private lateinit var view: GameSetupContract.View

    @Mock private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Mock private lateinit var getNewQuestionsUseCase: GetNewQuestionsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = GameSetupPresenter(view, sharedPreferencesManager, dataRepository, getNewQuestionsUseCase)
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
    fun onPlayButtonClicked_successful() {
        val questions = arrayOf(mock<TriviaQuestion>(), mock())
        whenever(getNewQuestionsUseCase.execute(any())).thenAnswer { invocation ->
            val resultListener = invocation.getArgument<GetNewQuestionsUseCase.ResultListener>(0)
            resultListener.onSuccess(questions)
        }

        presenter.onPlayButtonClicked()

        verify(view).disablePlayButton()
        verify(view).showLoading()
        verify(getNewQuestionsUseCase).execute(any())
        verify(view).hideLoading()
        verify(view).showNewGameScreen(questions)
        verify(view).enablePlayButton()
    }

    @Test
    fun onPlayButtonClicked_failure() {
        whenever(getNewQuestionsUseCase.execute(any())).thenAnswer { invocation ->
            val resultListener = invocation.getArgument<GetNewQuestionsUseCase.ResultListener>(0)
            resultListener.onFailure()
        }

        presenter.onPlayButtonClicked()

        verify(view).disablePlayButton()
        verify(view).showLoading()
        verify(getNewQuestionsUseCase).execute(any())
        verify(view).hideLoading()
        verify(view).showQuestionsRequestError()
        verify(view).enablePlayButton()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(getNewQuestionsUseCase)
    }

}
