package com.jahu.playground.features.quiz

import com.jahu.playground.features.quiz.random.RandomSequenceGenerator
import com.jahu.playground.trivia.TriviaQuestion

private const val ANSWERS_COUNT = 4

class QuizPresenter(
        private val view: QuizContract.View,
        private val questions: List<TriviaQuestion>,
        private val sequenceGenerator: RandomSequenceGenerator
) : QuizContract.Presenter {

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
