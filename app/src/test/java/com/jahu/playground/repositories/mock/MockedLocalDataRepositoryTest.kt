package com.jahu.playground.repositories.mock

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

}
