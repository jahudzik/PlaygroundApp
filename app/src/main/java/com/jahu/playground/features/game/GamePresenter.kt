package com.jahu.playground.features.game

import com.jahu.playground.features.game.random.RandomSequenceGenerator
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.games.AddGameResultUseCase

private const val ANSWERS_COUNT = 4

class GamePresenter(
        private val view: GameContract.View,
        private val questions: List<TriviaQuestion>,
        private val sequenceGenerator: RandomSequenceGenerator,
        private val addGameResultUseCase: AddGameResultUseCase
) : GameContract.Presenter {

    private val questionsCount = questions.size

    private var currentQuestionIndex = 0
    private var correctAnswerIndex = 0
    private var correctAnswersCount = 0

    override fun createView() {}

    override fun resumeView() {
        showNextQuestion()
    }

    override fun onAnswerChosen(answerIndex: Int) {
        if (answerIndex == correctAnswerIndex) {
            correctAnswersCount++
        }
        if (++currentQuestionIndex < questionsCount) {
            showNextQuestion()
        } else {
            addGameResultUseCase.execute(correctAnswersCount)
            view.showSummary(correctAnswersCount, questionsCount)
        }
    }

    private fun showNextQuestion() {
        val (question, answers) = prepareQuestion()
        view.showQuestion(question, answers)
    }

    private fun prepareQuestion(): Pair<String, List<String>> {
        val triviaQuestion = questions[currentQuestionIndex]
        val answers = mutableListOf<String>()
        val sequence = sequenceGenerator.generate(ANSWERS_COUNT)
        for (i in 0 until sequence.size) {
            if (sequence[i] == 0) {
                answers.add(triviaQuestion.correctAnswer)
                correctAnswerIndex = i
            } else {
                answers.add(triviaQuestion.incorrectAnswers[sequence[i] - 1])
            }
        }
        return Pair(triviaQuestion.question, answers)
    }

    override fun onReturnClicked() {
        view.navigateToDashboard()
    }

}
