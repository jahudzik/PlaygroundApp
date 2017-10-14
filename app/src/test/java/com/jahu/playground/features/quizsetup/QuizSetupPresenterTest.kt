package com.jahu.playground.features.quizsetup

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.trivia.TriviaResponse
import com.jahu.playground.trivia.TriviaService
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class QuizSetupPresenterTest {

    private lateinit var presenter: QuizSetupPresenter

    @Mock private lateinit var view: QuizSetupContract.View

    @Mock private lateinit var sharedPreferencesManager: SharedPreferencesManager

    @Mock private lateinit var dataRepository: LocalDataRepository

    @Mock private lateinit var triviaService: TriviaService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = QuizSetupPresenter(view, sharedPreferencesManager, dataRepository, triviaService)
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
    fun onStartQuizButtonClicked_failedResponse() {
        val call = buildFailureCall()
        whenever(triviaService.getGeneralQuestions()).thenReturn(call)

        presenter.onStartQuizButtonClicked()

        verify(triviaService).getGeneralQuestions()
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showQuestionsRequestError()
    }

    @Test
    fun onStartQuizButtonClicked_unsuccessfulResponse() {
        val call = buildSuccessfulCall(buildResponse(false, null))
        whenever(triviaService.getGeneralQuestions()).thenReturn(call)

        presenter.onStartQuizButtonClicked()

        verify(triviaService).getGeneralQuestions()
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showQuestionsRequestError()
    }

    @Test
    fun onStartQuizButtonClicked_successfulResponse() {
        val call = buildSuccessfulCall(buildResponse(true, mock()))
        whenever(triviaService.getGeneralQuestions()).thenReturn(call)

        presenter.onStartQuizButtonClicked()

        verify(triviaService).getGeneralQuestions()
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showNewQuizScreen()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManager)
        verifyNoMoreInteractions(dataRepository)
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(triviaService)
    }

    private fun buildFailureCall(): Call<TriviaResponse> {
        val call = mock<Call<TriviaResponse>>()
        whenever(call.enqueue(any())).thenAnswer { invocation ->
            val callback = invocation.getArgument<Callback<TriviaResponse>>(0)
            callback.onFailure(call, IOException())
        }
        return call
    }

    private fun buildSuccessfulCall(response: Response<TriviaResponse>): Call<TriviaResponse> {
        val call = mock<Call<TriviaResponse>>()
        whenever(call.enqueue(any())).thenAnswer { invocation ->
            val callback = invocation.getArgument<Callback<TriviaResponse>>(0)
            callback.onResponse(call, response)
        }
        return call
    }

    private fun buildResponse(successful: Boolean, triviaResponse: TriviaResponse?): Response<TriviaResponse> {
        val response = mock<Response<TriviaResponse>>()
        whenever(response.isSuccessful).thenReturn(successful)
        whenever(response.body()).thenReturn(triviaResponse)
        return response
    }

}
