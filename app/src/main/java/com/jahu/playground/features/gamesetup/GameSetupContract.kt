package com.jahu.playground.features.gamesetup

import com.jahu.playground.mvp.MvpPresenter
import com.jahu.playground.trivia.TriviaQuestion

interface GameSetupContract {

    interface View {

        fun showUserName(userName: String)

        fun showQuestionsRequestError()

        fun showLoading()

        fun hideLoading()

        fun enablePlayButton()

        fun disablePlayButton()

        fun showNewGameScreen(questions: Array<TriviaQuestion>)

    }

    interface Presenter : MvpPresenter {

        fun onPlayButtonClicked()

    }

}
