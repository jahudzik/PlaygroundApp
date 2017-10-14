package com.jahu.playground.trivia

import com.google.gson.annotations.SerializedName

data class TriviaResponse(
        @SerializedName("response_code")
        val responseCode: Int,
        val results: Array<TriviaQuestion>
)
