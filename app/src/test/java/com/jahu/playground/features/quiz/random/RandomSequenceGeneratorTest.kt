package com.jahu.playground.features.quiz.random

import com.nhaarman.mockito_kotlin.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RandomSequenceGeneratorTest {

    @Mock private lateinit var numberGenerator: RandomNumberGenerator

    private lateinit var sequenceGenerator: RandomSequenceGenerator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sequenceGenerator = RandomSequenceGenerator(numberGenerator)
    }

    @Test
    fun generate_0123() {
        prepareNumberGenerator(0, 0, 0, 0)

        val result = sequenceGenerator.generate(4)

        verifyExpectedList(listOf(0, 1, 2, 3), result)
    }

    @Test
    fun generate_3210() {
        prepareNumberGenerator(3, 2, 1, 0)

        val result = sequenceGenerator.generate(4)

        verifyExpectedList(listOf(3, 2, 1, 0), result)
    }

    @Test
    fun generate_2013() {
        prepareNumberGenerator(2, 0, 0, 0)

        val result = sequenceGenerator.generate(4)

        verifyExpectedList(listOf(2, 0, 1, 3), result)
    }

    @Test
    fun generate_3021() {
        prepareNumberGenerator(3, 0, 1, 0)

        val result = sequenceGenerator.generate(4)

        verifyExpectedList(listOf(3, 0, 2, 1), result)
    }

    @Test
    fun generate_1320() {
        prepareNumberGenerator(1, 2, 1, 0)

        val result = sequenceGenerator.generate(4)

        verifyExpectedList(listOf(1, 3, 2, 0), result)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(numberGenerator)
    }

    private fun prepareNumberGenerator(vararg values: Int) {
        whenever(numberGenerator.nextNumber(any()))
                .thenReturn(values[0])
                .thenReturn(values[1])
                .thenReturn(values[2])
                .thenReturn(values[3])
    }

    private fun verifyExpectedList(expectedList: List<Int>, result: List<Int>) {
        verify(numberGenerator, times(4)).nextNumber(any())
        assertEquals(expectedList, result)
    }

}
