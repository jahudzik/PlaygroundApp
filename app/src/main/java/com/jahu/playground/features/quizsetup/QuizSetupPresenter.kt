package com.jahu.playground.features.quizsetup

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.GetNewQuestionsUseCase

class QuizSetupPresenter(
        private val view: QuizSetupContract.View,
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val dataRepository: LocalDataRepository,
        private val getNewQuestionsUseCase: GetNewQuestionsUseCase
) : QuizSetupContract.Presenter {

    override fun createView() {}

    override fun resumeView() {
        val user = getActualUser()
        view.showUserName(user.firstName)
    }

    override fun onPlayButtonClicked() {
        view.disablePlayButton()
        view.showLoading()
        getNewQuestionsUseCase.execute(object : GetNewQuestionsUseCase.ResultListener {
            override fun onSuccess(questions: Array<TriviaQuestion>) {
                view.hideLoading()
                view.showNewQuizScreen(questions)
            }

            override fun onFailure() {
                view.hideLoading()
                view.showQuestionsRequestError()
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
