package com.jahu.playground.usecases.games

import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.trivia.TriviaResponse
import com.jahu.playground.trivia.TriviaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

private const val RESPONSE_CODE_SUCCESS = 0

class GetNewQuestionsUseCase(
        private val triviaService: TriviaService
) {

    fun execute(resultListener: ResultListener) {
        triviaService.getGeneralQuestions().enqueue(object : Callback<TriviaResponse> {
            override fun onResponse(call: Call<TriviaResponse>?, response: Response<TriviaResponse>?) {
                handleResponse(response, resultListener)
            }

            override fun onFailure(call: Call<TriviaResponse>?, throwable: Throwable?) {
                handleFailure(resultListener, throwable)
            }
        })

    }

    private fun handleResponse(response: Response<TriviaResponse>?, resultListener: ResultListener) {
        if (response != null && response.isSuccessful) {
            val triviaResponse = response.body() as TriviaResponse
            if (triviaResponse.responseCode == RESPONSE_CODE_SUCCESS) {
                resultListener.onSuccess(triviaResponse.results)
            } else {
                resultListener.onFailure()
                Timber.e("Failed to fetch the questions - unexpected response code [${triviaResponse.responseCode}]")
            }
        } else {
            resultListener.onFailure()
            Timber.e("Failed to fetch the questions - unsuccessful response")
        }
    }

    private fun handleFailure(resultListener: ResultListener, throwable: Throwable?) {
        resultListener.onFailure()
        Timber.e(throwable, "Failed to fetch the questions")
    }

    interface ResultListener {

        fun onSuccess(questions: Array<TriviaQuestion>)

        fun onFailure()

    }

}
