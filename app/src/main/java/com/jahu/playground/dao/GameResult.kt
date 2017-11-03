package com.jahu.playground.dao

data class GameResult(
        val userId: Long,
        val correctAnswersCount: Int,
        val timestamp: Long
)
