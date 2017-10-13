package com.jahu.playground.features.quizsetup

import com.jahu.playground.mvp.BasePresenter

interface QuizSetupContract {

    interface View {

        fun showUserName(userName: String)

        fun showNewQuizScreen()

    }

    interface Presenter : BasePresenter {

        fun onStartQuizButtonClicked()

    }

}
