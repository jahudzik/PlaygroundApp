package com.jahu.playground.repositories.memory

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
    fun getUserById_existing() {
        val user = MockedLocalDataRepository.getUserById(3)

        assertNotNull(user)
        assertEquals(MockedLocalDataRepository.user3, user)
    }

    @Test
    fun getUserById_notExisting() {
        val user = MockedLocalDataRepository.getUserById(4)

        assertNull(user)
    }

    @Test
    fun getUserByNick_existing() {
        val user = MockedLocalDataRepository.getUserByNick("hippo")

        assertNotNull(user)
        assertEquals(MockedLocalDataRepository.user2, user)
    }

    @Test
    fun getUserByNick_notExisting() {
        val user = MockedLocalDataRepository.getUserByNick("unknown")

        assertNull(user)
    }

    @Test
    fun addUser_expected() {
        val newUser = User(4, "newby", "Adam", "Smith")
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
    fun updateUser_expected() {
        val newUser2 = MockedLocalDataRepository.user2.copy(nick = "newby")
        val expectedItems = setOf(
                MockedLocalDataRepository.user1,
                newUser2,
                MockedLocalDataRepository.user3
        )

        MockedLocalDataRepository.updateUser(newUser2)
        val users = MockedLocalDataRepository.getAllUsers()

        assertEquals(expectedItems, users)
    }

    @Test
    fun getHighestUserId_initialState() {
        val id = MockedLocalDataRepository.getHighestUserId()

        assertEquals(3L, id)
    }

    @Test
    fun getHighestUserId_extraUserWithLowerId() {
        MockedLocalDataRepository.addUser(User(0, "test", "test", "test"))

        val id = MockedLocalDataRepository.getHighestUserId()

        assertEquals(3L, id)
    }

    @Test
    fun getHighestUserId_extraUserWithHigherId() {
        MockedLocalDataRepository.addUser(User(5, "test", "test", "test"))

        val id = MockedLocalDataRepository.getHighestUserId()

        assertEquals(5L, id)
    }

    @Test
    fun getGameResultsById_initialEmpty() {
        val results = MockedLocalDataRepository.getGameResultsById(1)

        assertEquals(emptyList<GameResult>(), results)
    }

    @Test
    fun getGameResultsById_singleUser() {
        val userId = 3L
        val inputResults = listOf(
                GameResult(userId, 5, 300),
                GameResult(userId, 7, 304),
                GameResult(userId, 4, 444))
        inputResults.map { MockedLocalDataRepository.addGameResult(it) }

        val outputResults = MockedLocalDataRepository.getGameResultsById(userId)

        assertEquals(inputResults, outputResults)
    }

    @Test
    fun getGameResultsById_multipleUsers() {
        val inputResults = listOf(
                GameResult(1, 5, 10),
                GameResult(1, 7, 20),
                GameResult(3, 2, 30),
                GameResult(1, 10, 33),
                GameResult(3, 4, 45),
                GameResult(2, 9, 50))
        val expectedResults = listOf(
                GameResult(3, 2, 30),
                GameResult(3, 4, 45)
        )
        inputResults.map { MockedLocalDataRepository.addGameResult(it) }

        val outputResults = MockedLocalDataRepository.getGameResultsById(3)

        assertEquals(expectedResults, outputResults)
    }

}
