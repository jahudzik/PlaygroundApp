package com.jahu.playground.usecases

import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.trivia.TriviaResponse
import com.jahu.playground.trivia.TriviaService
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class GetNewQuestionsUseCaseTest {

    @Mock private lateinit var triviaService: TriviaService

    @Mock private lateinit var resultListener: GetNewQuestionsUseCase.ResultListener

    private lateinit var useCase: GetNewQuestionsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = GetNewQuestionsUseCase(triviaService)
    }

    @Test
    fun execute_failedResponse() {
        val call = buildFailureCall()
        whenever(triviaService.getGeneralQuestions()).thenReturn(call)

        useCase.execute(resultListener)

        verify(triviaService).getGeneralQuestions()
        verify(resultListener).onFailure()
    }

    @Test
    fun execute_unsuccessfulResponse() {
        val call = buildSuccessfulCall(buildResponse(false, null))
        whenever(triviaService.getGeneralQuestions()).thenReturn(call)

        useCase.execute(resultListener)

        verify(triviaService).getGeneralQuestions()
        verify(resultListener).onFailure()
    }

    @Test
    fun execute_unexpectedResponseCode() {
        val triviaResponse = mock<TriviaResponse>()
        whenever(triviaResponse.responseCode).thenReturn(1)
        val call = buildSuccessfulCall(buildResponse(true, triviaResponse))
        whenever(triviaService.getGeneralQuestions()).thenReturn(call)

        useCase.execute(resultListener)

        verify(triviaService).getGeneralQuestions()
        verify(resultListener).onFailure()
    }

    @Test
    fun execute_successfulResponse() {
        val results = arrayOf(mock(), mock(), mock<TriviaQuestion>())
        val triviaResponse = mock<TriviaResponse>()
        whenever(triviaResponse.results).thenReturn(results)
        val call = buildSuccessfulCall(buildResponse(true, triviaResponse))
        whenever(triviaService.getGeneralQuestions()).thenReturn(call)

        useCase.execute(resultListener)

        verify(triviaService).getGeneralQuestions()
        verify(resultListener).onSuccess(results)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(triviaService)
        verifyNoMoreInteractions(resultListener)
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
