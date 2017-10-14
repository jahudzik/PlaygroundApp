package com.jahu.playground.trivia

import retrofit2.Call
import retrofit2.http.GET

interface TriviaService {

    @GET("api.php?amount=10&category=9&difficulty=easy&type=multiple")
    fun getGeneralQuestions(): Call<TriviaResponse>

}
