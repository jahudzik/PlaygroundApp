package com.jahu.playground.features.quiz

import com.jahu.playground.mvp.BasePresenter

interface QuizContract {

    interface View {

        fun showQuestion(question: String, answers: List<String>)

        fun showSummary(correctAnswersCount: Int, questionsCount: Int)

        fun navigateToDashboard()

    }

    interface Presenter : BasePresenter {

        fun onAnswerChosen(answerIndex: Int)

        fun onReturnClicked()

    }

}
