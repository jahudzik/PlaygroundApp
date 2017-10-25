package com.jahu.playground.features.quizsetup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jahu.playground.PlaygroundApplication
import com.jahu.playground.R
import com.jahu.playground.features.quiz.QuizActivity
import com.jahu.playground.mvp.BaseFragment
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.mock.MockedLocalDataRepository
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.GetNewQuestionsUseCase
import kotlinx.android.synthetic.main.fragment_quiz_setup.*

class QuizSetupFragment : BaseFragment<QuizSetupContract.Presenter>(), QuizSetupContract.View {

    companion object {
        fun newInstance() = QuizSetupFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesManager = SharedPreferencesManager(activity)
        val triviaService = (activity.application as PlaygroundApplication).getTriviaService()
        val getNewQuestionsUseCase = GetNewQuestionsUseCase(triviaService)
        presenter = QuizSetupPresenter(this, preferencesManager, MockedLocalDataRepository, getNewQuestionsUseCase)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_quiz_setup, container, false)
    }

    override fun onResume() {
        super.onResume()
        playButton.setOnClickListener { presenter.onPlayButtonClicked() }
    }

    override fun showUserName(userName: String) {
        welcomeMessageTextView.text = getString(R.string.welcome_message, userName)
    }

    override fun showQuestionsRequestError() {
        Toast.makeText(activity, "Failed to fetch the questions", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        questionsLoader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        questionsLoader.visibility = View.GONE
    }

    override fun enablePlayButton() {
        playButton.isEnabled = true
    }

    override fun disablePlayButton() {
        playButton.isEnabled = false
    }

    override fun showNewQuizScreen(questions: Array<TriviaQuestion>) {
        val intent = Intent(activity, QuizActivity::class.java)
        intent.putExtra(QuizActivity.BUNDLE_QUESTIONS_KEY, questions)
        startActivity(intent)
    }

}
