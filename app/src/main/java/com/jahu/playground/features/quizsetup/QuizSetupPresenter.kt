package com.jahu.playground.features.quizsetup

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.trivia.TriviaResponse
import com.jahu.playground.trivia.TriviaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override fun onStartQuizButtonClicked() {
        triviaService.getGeneralQuestions().enqueue(object : Callback<TriviaResponse> {
            override fun onFailure(call: Call<TriviaResponse>?, t: Throwable?) {
                // TODO handle failure
            }

            override fun onResponse(call: Call<TriviaResponse>?, response: Response<TriviaResponse>?) {
                // TODO handle response
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
