package com.jahu.playground.repository.mock

import com.jahu.playground.dao.User
import org.junit.Assert.*
import org.junit.Test

class MockedLocalDataRepositoryTest {

    private val repository = MockedLocalDataRepository()

    @Test
    fun getAllUsers_expected() {
        val expectedItems = setOf(
                MockedLocalDataRepository.USER1,
                MockedLocalDataRepository.USER2,
                MockedLocalDataRepository.USER3
        )

        val users = repository.getAllUsers()

        assertEquals(expectedItems, users)
    }

    @Test
    fun getUserByNick_existing() {
        val user = repository.getUserByNick("hippo")

        assertNotNull(user)
        assertEquals(MockedLocalDataRepository.USER2, user)
    }

    @Test
    fun getUserByNick_unknown() {
        val user = repository.getUserByNick("unknown")

        assertNull(user)
    }

    @Test
    fun addUser_expected() {
        val newUser = User("newby", "Adam", "Smith")
        val expectedItems = setOf(
                MockedLocalDataRepository.USER1,
                MockedLocalDataRepository.USER2,
                MockedLocalDataRepository.USER3,
                newUser
        )

        repository.addUser(newUser)
        val users = repository.getAllUsers()

        assertEquals(expectedItems, users)
    }

}