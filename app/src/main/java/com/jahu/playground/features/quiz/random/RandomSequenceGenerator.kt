package com.jahu.playground.features.quiz.random

class RandomSequenceGenerator(
        private val numberGenerator: RandomNumberGenerator
) {

    fun generate(size: Int): List<Int> {
        val baseList = (0 until size).toMutableList()
        return (size - 1 downTo 0)
                .map { numberGenerator.nextNumber(it + 1) }
                .map { baseList.removeAt(it) }
    }

}
