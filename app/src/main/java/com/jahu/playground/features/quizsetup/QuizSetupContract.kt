package com.jahu.playground.features.quizsetup

import com.jahu.playground.mvp.BasePresenter
import com.jahu.playground.trivia.TriviaQuestion

interface QuizSetupContract {

    interface View {

        fun showUserName(userName: String)

        fun showQuestionsRequestError()

        fun showLoading()

        fun hideLoading()

        fun enablePlayButton()

        fun disablePlayButton()

        fun showNewQuizScreen(questions: Array<TriviaQuestion>)

    }

    interface Presenter : BasePresenter {

        fun onPlayButtonClicked()

    }

}
