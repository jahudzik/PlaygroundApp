package com.jahu.playground.dao

import com.orm.SugarRecord

data class GameResult(
        val userId: Long = 0,
        val correctAnswersCount: Int = 0,
        val timestamp: Long = 0
) : SugarRecord<GameResult>()
