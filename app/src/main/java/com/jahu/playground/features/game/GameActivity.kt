package com.jahu.playground.features.game

import android.app.Fragment
import android.os.Bundle
import com.jahu.playground.R
import com.jahu.playground.features.game.random.RandomNumberGenerator
import com.jahu.playground.features.game.random.RandomSequenceGenerator
import com.jahu.playground.features.game.time.TimeProvider
import com.jahu.playground.mvp.MvpActivity
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.memory.MockedLocalDataRepository
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.games.AddGameResultUseCase

class GameActivity : MvpActivity<GameContract.Presenter>(), GameContract.View,
        QuestionFragment.EventListener, SummaryFragment.EventListener {

    companion object {
        const val BUNDLE_QUESTIONS_KEY = "questions"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val sharedPreferencesManager = SharedPreferencesManager(this)
        val addGameResultUseCase = AddGameResultUseCase(sharedPreferencesManager, MockedLocalDataRepository, TimeProvider())
        presenter = GamePresenter(this, getQuestionsList(), RandomSequenceGenerator(RandomNumberGenerator()), addGameResultUseCase)
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
        switchFragment(QuestionFragment.newInstance(question, answers))
    }

    override fun onAnswerChosen(index: Int) {
        presenter.onAnswerChosen(index)
    }

    override fun showSummary(correctAnswersCount: Int, questionsCount: Int) {
        switchFragment(SummaryFragment.newInstance(correctAnswersCount, questionsCount))
    }

    override fun onReturnClicked() {
        presenter.onReturnClicked()
    }

    override fun navigateToDashboard() {
        finish()
    }

    private fun switchFragment(fragment: Fragment) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.gameContainer, fragment)
                .commit()
    }

}
