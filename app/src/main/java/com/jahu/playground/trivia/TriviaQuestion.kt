package com.jahu.playground.trivia

import com.google.gson.annotations.SerializedName

data class TriviaQuestion(
        val category: String,
        val type: String,
        val difficulty: String,
        val question: String,
        @SerializedName("correct_answer")
        val correctAnswer: String,
        @SerializedName("incorrect_answers")
        val incorrectAnswers: Array<String>
) {

    @SuppressWarnings("ReturnCount")
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }

        return question == (other as TriviaQuestion).question
    }

    override fun hashCode(): Int {
        return question.hashCode()
    }

}
