package com.jahu.playground.features.game

import com.jahu.playground.mvp.MvpPresenter

interface GameContract {

    interface View {

        fun showQuestion(question: String, answers: List<String>)

        fun showSummary(correctAnswersCount: Int, questionsCount: Int)

        fun navigateToDashboard()

    }

    interface Presenter : MvpPresenter {

        fun onAnswerChosen(answerIndex: Int)

        fun onReturnClicked()

    }

}
