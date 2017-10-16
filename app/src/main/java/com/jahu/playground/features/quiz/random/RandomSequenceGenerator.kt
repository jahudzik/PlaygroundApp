package com.jahu.playground.features.quiz.random

class RandomSequenceGenerator(
        private val numberGenerator: RandomNumberGenerator
) {

    fun generate(size: Int): List<Int> {
        val baseList = (0 until size).toMutableList()
        return (0 until size)
                .map { numberGenerator.nextNumber(it) }
                .map { baseList.removeAt(it) }
    }

}
