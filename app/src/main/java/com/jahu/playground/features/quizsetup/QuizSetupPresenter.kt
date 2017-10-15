package com.jahu.playground.features.quizsetup

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.trivia.TriviaResponse
import com.jahu.playground.trivia.TriviaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class QuizSetupPresenter(
        private val view: QuizSetupContract.View,
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val dataRepository: LocalDataRepository,
        private val triviaService: TriviaService
) : QuizSetupContract.Presenter {

    override fun resumeView() {
        val user = getActualUser()
        view.showUserName(user.firstName)
    }

    override fun onPlayButtonClicked() {
        view.disablePlayButton()
        view.showLoading()
        triviaService.getGeneralQuestions().enqueue(object : Callback<TriviaResponse> {
            override fun onFailure(call: Call<TriviaResponse>?, throwable: Throwable?) {
                view.hideLoading()
                view.showQuestionsRequestError()
                view.enablePlayButton()
                Timber.e(throwable, "Failed to fetch the questions")
            }

            override fun onResponse(call: Call<TriviaResponse>?, response: Response<TriviaResponse>?) {
                view.hideLoading()
                if (response != null && response.isSuccessful) {
                    // TODO handle received questions
                    view.showNewQuizScreen()
                } else {
                    view.showQuestionsRequestError()
                    Timber.e("Failed to fetch the questions - unsuccessful response")
                }
                view.enablePlayButton()
            }

        })
    }

    private fun getActualUser(): User {
        val nick = sharedPreferencesManager.getActualUserNick() ?:
                throw IllegalStateException("No information about current user (nick)")
        return dataRepository.getUserByNick(nick) ?:
                throw IllegalStateException("No information about current user (user data)")
    }

}
