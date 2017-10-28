package com.jahu.playground.repositories.mock

import com.jahu.playground.dao.GameResult
import com.jahu.playground.dao.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MockedLocalDataRepositoryTest {

    @Before
    fun setUp() {
        MockedLocalDataRepository.reset()
    }

    @Test
    fun getAllUsers_expected() {
        val expectedItems = setOf(
                MockedLocalDataRepository.user1,
                MockedLocalDataRepository.user2,
                MockedLocalDataRepository.user3
        )

        val users = MockedLocalDataRepository.getAllUsers()

        assertEquals(expectedItems, users)
    }

    @Test
    fun getUserByNick_existing() {
        val user = MockedLocalDataRepository.getUserByNick("hippo")

        assertNotNull(user)
        assertEquals(MockedLocalDataRepository.user2, user)
    }

    @Test
    fun getUserByNick_unknown() {
        val user = MockedLocalDataRepository.getUserByNick("unknown")

        assertNull(user)
    }

    @Test
    fun addUser_expected() {
        val newUser = User("newby", "Adam", "Smith")
        val expectedItems = setOf(
                MockedLocalDataRepository.user1,
                MockedLocalDataRepository.user2,
                MockedLocalDataRepository.user3,
                newUser
        )

        MockedLocalDataRepository.addUser(newUser)
        val users = MockedLocalDataRepository.getAllUsers()

        assertEquals(expectedItems, users)
    }

    @Test
    fun getGameResultsByNick_initialEmpty() {
        val results = MockedLocalDataRepository.getGameResultsByNick("someNick")

        assertEquals(emptyList<GameResult>(), results)
    }

    @Test
    fun getGameResultsByNick_singleUser() {
        val nick = "me"
        val inputResults = listOf(
                GameResult(nick, 5, 300),
                GameResult(nick, 7, 304),
                GameResult(nick, 4, 444))
        inputResults.map { MockedLocalDataRepository.addGameResult(it) }

        val outputResults = MockedLocalDataRepository.getGameResultsByNick(nick)

        assertEquals(inputResults, outputResults)
    }

    @Test
    fun getGameResultsByNick_multipleUsers() {
        val nick1 = "first"
        val nick2 = "second"
        val nick3 = "third"
        val inputResults = listOf(
                GameResult(nick1, 5, 10),
                GameResult(nick1, 7, 20),
                GameResult(nick3, 2, 30),
                GameResult(nick1, 10, 33),
                GameResult(nick3, 4, 45),
                GameResult(nick2, 9, 50))
        val expectedResults = listOf(
                GameResult(nick3, 2, 30),
                GameResult(nick3, 4, 45)
        )
        inputResults.map { MockedLocalDataRepository.addGameResult(it) }

        val outputResults = MockedLocalDataRepository.getGameResultsByNick(nick3)

        assertEquals(expectedResults, outputResults)
    }

}
