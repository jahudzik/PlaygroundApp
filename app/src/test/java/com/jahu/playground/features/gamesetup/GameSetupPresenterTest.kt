package com.jahu.playground.features.gamesetup

import com.jahu.playground.data.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.games.GetNewQuestionsUseCase
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
        whenever(sharedPreferencesManager.getActualUserId()).thenReturn(-1)

        var exceptionThrown = false
        try {
            presenter.resumeView()
        } catch (e: IllegalStateException) {
            exceptionThrown = true
            verify(sharedPreferencesManager).getActualUserId()
        }
        assertTrue(exceptionThrown)
    }

    @Test
    fun resumeView_noUserData() {
        val userId = 1L
        whenever(sharedPreferencesManager.getActualUserId()).thenReturn(userId)
        whenever(dataRepository.getUserById(userId)).thenReturn(null)

        var exceptionThrown = false
        try {
            presenter.resumeView()
        } catch (e: IllegalStateException) {
            exceptionThrown = true
            verify(sharedPreferencesManager).getActualUserId()
            verify(dataRepository).getUserById(eq(userId))
        }
        assertTrue(exceptionThrown)
    }

    @Test
    fun resumeView_expected() {
        val userId = 1L
        val user = User(userId, "John", "Smith", "someNick")
        whenever(sharedPreferencesManager.getActualUserId()).thenReturn(userId)
        whenever(dataRepository.getUserById(userId)).thenReturn(user)

        presenter.resumeView()

        verify(sharedPreferencesManager).getActualUserId()
        verify(dataRepository).getUserById(eq(userId))
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
