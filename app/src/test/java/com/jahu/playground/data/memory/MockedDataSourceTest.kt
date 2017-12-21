package com.jahu.playground.data.memory

import com.jahu.playground.data.entities.GameResult
import com.jahu.playground.data.entities.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MockedDataSourceTest {

    @Before
    fun setUp() {
        MockedDataSource.reset()
    }

    @Test
    fun getAllUsers_expected() {
        val expectedItems = setOf(
                MockedDataSource.user1,
                MockedDataSource.user2,
                MockedDataSource.user3
        )

        val users = MockedDataSource.getAllUsers()

        assertEquals(expectedItems, users)
    }

    @Test
    fun getUserById_existing() {
        val user = MockedDataSource.getUserById(3)

        assertNotNull(user)
        assertEquals(MockedDataSource.user3, user)
    }

    @Test
    fun getUserById_notExisting() {
        val user = MockedDataSource.getUserById(4)

        assertNull(user)
    }

    @Test
    fun getUserByNick_existing() {
        val user = MockedDataSource.getUserByNick("hippo")

        assertNotNull(user)
        assertEquals(MockedDataSource.user2, user)
    }

    @Test
    fun getUserByNick_notExisting() {
        val user = MockedDataSource.getUserByNick("unknown")

        assertNull(user)
    }

    @Test
    fun addUser_expected() {
        val newUser = User(4, "newby", "Adam", "Smith")
        val expectedItems = setOf(
                MockedDataSource.user1,
                MockedDataSource.user2,
                MockedDataSource.user3,
                newUser
        )

        MockedDataSource.addUser(newUser)
        val users = MockedDataSource.getAllUsers()

        assertEquals(expectedItems, users)
    }

    @Test
    fun updateUser_expected() {
        val newUser2 = MockedDataSource.user2.copy(nick = "newby")
        val expectedItems = setOf(
                MockedDataSource.user1,
                newUser2,
                MockedDataSource.user3
        )

        MockedDataSource.updateUser(newUser2)
        val users = MockedDataSource.getAllUsers()

        assertEquals(expectedItems, users)
    }

    @Test
    fun getHighestUserId_initialState() {
        val id = MockedDataSource.getHighestUserId()

        assertEquals(3L, id)
    }

    @Test
    fun getHighestUserId_extraUserWithLowerId() {
        MockedDataSource.addUser(User(0, "test", "test", "test"))

        val id = MockedDataSource.getHighestUserId()

        assertEquals(3L, id)
    }

    @Test
    fun getHighestUserId_extraUserWithHigherId() {
        MockedDataSource.addUser(User(5, "test", "test", "test"))

        val id = MockedDataSource.getHighestUserId()

        assertEquals(5L, id)
    }

    @Test
    fun getGameResultsByUserId_initialEmpty() {
        val results = MockedDataSource.getGameResultsByUserId(1)

        assertEquals(emptyList<GameResult>(), results)
    }

    @Test
    fun getGameResultsByUserId_singleUser() {
        val userId = 3L
        val inputResults = listOf(
                GameResult(userId, 5, 300),
                GameResult(userId, 7, 304),
                GameResult(userId, 4, 444))
        inputResults.map { MockedDataSource.addGameResult(it) }

        val outputResults = MockedDataSource.getGameResultsByUserId(userId)

        assertEquals(inputResults, outputResults)
    }

    @Test
    fun getGameResultsByUserId_multipleUsers() {
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
        inputResults.map { MockedDataSource.addGameResult(it) }

        val outputResults = MockedDataSource.getGameResultsByUserId(3)

        assertEquals(expectedResults, outputResults)
    }

}
