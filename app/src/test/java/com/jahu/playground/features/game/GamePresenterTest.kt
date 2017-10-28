package com.jahu.playground.features.game

import com.jahu.playground.features.game.random.RandomSequenceGenerator
import com.jahu.playground.trivia.TriviaQuestion
import com.jahu.playground.usecases.AddGameResultUseCase
import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

private const val QUESTION = "Question"

class GamePresenterTest {

    private lateinit var presenter: GamePresenter

    @Mock private lateinit var view: GameContract.View

    @Mock private lateinit var sequenceGenerator: RandomSequenceGenerator

    @Mock private lateinit var addGameResultUseCase: AddGameResultUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun showNextQuestion_shuffleAnswers0123() {
        initWithSingleQuestion(0, 1, 2, 3)

        presenter.resumeView()

        verifyExpectedAnswers(listOf("yes", "no_1", "no_2", "no_3"))
    }

    @Test
    fun showNextQuestion_shuffleAnswers3210() {
        initWithSingleQuestion(3, 2, 1, 0)

        presenter.resumeView()

        verifyExpectedAnswers(listOf("no_3", "no_2", "no_1", "yes"))
    }

    @Test
    fun showNextQuestion_shuffleAnswers2301() {
        initWithSingleQuestion(2, 3, 0, 1)

        presenter.resumeView()

        verifyExpectedAnswers(listOf("no_2", "no_3", "yes", "no_1"))
    }

    @Test
    fun showNextQuestion_shuffleAnswers1023() {
        initWithSingleQuestion(1, 0, 2, 3)

        presenter.resumeView()

        verifyExpectedAnswers(listOf("no_1", "yes", "no_2", "no_3"))
    }

    @Test
    fun onAnswerChosen_allAnswersIncorrect() {
        initQuestions(
                listOf(1, 2, 0, 3),
                listOf(2, 3, 0, 1),
                listOf(0, 2, 1, 3)
        )

        presenter.resumeView()
        presenter.onAnswerChosen(3)     // incorrect
        presenter.onAnswerChosen(3)     // incorrect
        presenter.onAnswerChosen(3)     // incorrect

        verifyCorrectAnswersCount(0, 3)
    }


    @Test
    fun onAnswerChosen_someAnswersCorrect1() {
        initQuestions(
                listOf(2, 1, 0, 3),
                listOf(1, 0, 3, 2),
                listOf(1, 2, 3, 0),
                listOf(0, 1, 2, 3),
                listOf(1, 3, 0, 2),
                listOf(2, 3, 0, 1)
        )

        presenter.resumeView()
        presenter.onAnswerChosen(1)     // incorrect
        presenter.onAnswerChosen(1)     // correct
        presenter.onAnswerChosen(2)     // incorrect
        presenter.onAnswerChosen(0)     // correct
        presenter.onAnswerChosen(3)     // incorrect
        presenter.onAnswerChosen(3)     // incorrect

        verifyCorrectAnswersCount(2, 6)
    }

    @Test
    fun onAnswerChosen_someAnswersCorrect2() {
        initQuestions(
                listOf(2, 1, 0, 3),
                listOf(2, 1, 0, 3),
                listOf(1, 0, 3, 2),
                listOf(1, 2, 3, 0),
                listOf(0, 1, 2, 3),
                listOf(1, 3, 0, 2),
                listOf(2, 3, 0, 1),
                listOf(2, 3, 0, 1)
        )

        presenter.resumeView()
        presenter.onAnswerChosen(2)     // correct
        presenter.onAnswerChosen(2)     // correct
        presenter.onAnswerChosen(1)     // correct
        presenter.onAnswerChosen(3)     // correct
        presenter.onAnswerChosen(1)     // incorrect
        presenter.onAnswerChosen(2)     // correct
        presenter.onAnswerChosen(3)     // incorrect
        presenter.onAnswerChosen(0)     // incorrect

        verifyCorrectAnswersCount(5, 8)
    }

    @Test
    fun onAnswerChosen_allAnswersCorrect() {
        initQuestions(
                listOf(1, 2, 0, 3),
                listOf(3, 1, 2, 0),
                listOf(3, 0, 1, 2),
                listOf(2, 1, 3, 0)
        )

        presenter.resumeView()
        presenter.onAnswerChosen(2)     // correct
        presenter.onAnswerChosen(3)     // correct
        presenter.onAnswerChosen(1)     // correct
        presenter.onAnswerChosen(3)     // correct

        verifyCorrectAnswersCount(4, 4)
    }

    @Test
    fun onReturnClicked_expected() {
        presenter = GamePresenter(view, emptyList(), sequenceGenerator, addGameResultUseCase)

        presenter.onReturnClicked()

        verify(view).navigateToDashboard()
    }

    private fun initWithSingleQuestion(vararg answersSequence: Int) {
        val triviaQuestion = buildTriviaQuestion(QUESTION, "yes", arrayOf("no_1", "no_2", "no_3"))
        whenever(sequenceGenerator.generate(any())).thenReturn(answersSequence.asList())
        presenter = GamePresenter(view, listOf(triviaQuestion), sequenceGenerator, addGameResultUseCase)
    }

    private fun initQuestions(firstAnswers: List<Int>, vararg followingAnswers: List<Int>) {
        val questions = buildTriviaQuestionMocksList(followingAnswers.size + 1)
        whenever(sequenceGenerator.generate(any())).thenReturn(firstAnswers, *followingAnswers)
        presenter = GamePresenter(view, questions, sequenceGenerator, addGameResultUseCase)
    }

    private fun buildTriviaQuestion(question: String,
                                    correctAnswer: String,
                                    incorrectAnswers: Array<String>): TriviaQuestion {
        return TriviaQuestion(
                "9",
                "multiple",
                "easy",
                question,
                correctAnswer,
                incorrectAnswers
        )
    }

    private fun buildTriviaQuestionMocksList(size: Int): List<TriviaQuestion> {
        val list = mutableListOf<TriviaQuestion>()
        for (i in 1..size) {
            list.add(buildTriviaQuestionMock())
        }
        return list
    }

    private fun buildTriviaQuestionMock(): TriviaQuestion {
        val triviaQuestion = mock<TriviaQuestion>()
        whenever(triviaQuestion.question).thenReturn("some question")
        whenever(triviaQuestion.correctAnswer).thenReturn("yes")
        whenever(triviaQuestion.incorrectAnswers).thenReturn(arrayOf("no_1", "no_2", "no_3"))
        return triviaQuestion
    }

    private fun verifyExpectedAnswers(expectedAnswers: List<String>) {
        verify(sequenceGenerator).generate(4)
        verify(view).showQuestion(QUESTION, expectedAnswers)
    }

    private fun verifyCorrectAnswersCount(expectedCorrectAnswersCount: Int, expectedQuestionsCount: Int) {
        verify(sequenceGenerator, times(expectedQuestionsCount)).generate(4)
        verify(view, times(expectedQuestionsCount)).showQuestion(any(), any())
        verify(view).showSummary(expectedCorrectAnswersCount, expectedQuestionsCount)
        verify(addGameResultUseCase).execute(expectedCorrectAnswersCount)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(sequenceGenerator)
        verifyNoMoreInteractions(addGameResultUseCase)
    }

}

