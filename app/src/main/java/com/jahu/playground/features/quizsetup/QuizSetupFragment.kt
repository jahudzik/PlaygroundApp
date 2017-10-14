package com.jahu.playground.features.quizsetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jahu.playground.PlaygroundApplication
import com.jahu.playground.R
import com.jahu.playground.mvp.BaseFragment
import com.jahu.playground.repositories.SharedPreferencesManager
import com.jahu.playground.repositories.mock.MockedLocalDataRepository
import kotlinx.android.synthetic.main.fragment_quiz_setup.*

class QuizSetupFragment : BaseFragment<QuizSetupContract.Presenter>(), QuizSetupContract.View {

    companion object {
        fun newInstance() = QuizSetupFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesManager = SharedPreferencesManager(activity)
        val triviaService = (activity.application as PlaygroundApplication).getTriviaService()
        presenter = QuizSetupPresenter(this, preferencesManager, MockedLocalDataRepository, triviaService)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_quiz_setup, container, false)
    }

    override fun onResume() {
        super.onResume()
        startQuizButton.setOnClickListener { presenter.onStartQuizButtonClicked() }
    }

    override fun showUserName(userName: String) {
        welcomeMessageTextView.text = getString(R.string.welcome_message, userName)
    }

    override fun showNewQuizScreen() {
        Toast.makeText(activity, "New quiz screen", Toast.LENGTH_LONG).show()
    }

}
