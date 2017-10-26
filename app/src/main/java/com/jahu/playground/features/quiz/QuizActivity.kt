package com.jahu.playground.features.quiz

import android.os.Bundle
import com.jahu.playground.R
import com.jahu.playground.features.quiz.random.RandomNumberGenerator
import com.jahu.playground.features.quiz.random.RandomSequenceGenerator
import com.jahu.playground.mvp.BaseActivity
import com.jahu.playground.trivia.TriviaQuestion

class QuizActivity : BaseActivity<QuizPresenter>(), QuizContract.View,
        QuestionFragment.EventListener, SummaryFragment.EventListener {

    companion object {
        const val BUNDLE_QUESTIONS_KEY = "questions"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        presenter = QuizPresenter(this, getQuestionsList(), RandomSequenceGenerator(RandomNumberGenerator()))
    }

    private fun getQuestionsList(): List<TriviaQuestion> {
        val questions = intent.getSerializableExtra(BUNDLE_QUESTIONS_KEY)
        return if (questions is Array<*>) {
            questions.map { it as TriviaQuestion }
        } else {
            emptyList()
        }
    }

    override fun showQuestion(question: String, answers: List<String>) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.quizContainer, QuestionFragment.newInstance(question, answers))
                .commit()
    }

    override fun onAnswerChosen(index: Int) {
        presenter.onAnswerChosen(index)
    }

    override fun showSummary(correctAnswersCount: Int, questionsCount: Int) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.quizContainer, SummaryFragment.newInstance(correctAnswersCount, questionsCount))
                .commit()
    }

    override fun onReturnClicked() {
        presenter.onReturnClicked()
    }

    override fun navigateToDashboard() {
        finish()
    }

}
