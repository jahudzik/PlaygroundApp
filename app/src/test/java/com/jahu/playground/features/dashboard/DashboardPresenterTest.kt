package com.jahu.playground.features.dashboard

import com.jahu.playground.dao.User
import com.jahu.playground.repositories.LocalDataRepository
import com.jahu.playground.repositories.SharedPreferencesManager
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DashboardPresenterTest {

    private lateinit var presenter: DashboardPresenter

    @Mock
    private lateinit var viewMock: DashboardContract.View

    @Mock
    private lateinit var sharedPreferencesManagerMock: SharedPreferencesManager

    @Mock
    private lateinit var dataRepositoryMock: LocalDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DashboardPresenter(viewMock, sharedPreferencesManagerMock, dataRepositoryMock)
    }

    @Test
    fun resumeView_noUserNick() {
        whenever(sharedPreferencesManagerMock.getActualUserNick()).thenReturn(null)

        var exceptionThrown = false
        try {
            presenter.resumeView()
        } catch (e: IllegalStateException) {
            exceptionThrown = true
            verify(sharedPreferencesManagerMock).getActualUserNick()
        }
        assertTrue(exceptionThrown)
    }

    @Test
    fun resumeView_noUserData() {
        val nick = "someNick"
        whenever(sharedPreferencesManagerMock.getActualUserNick()).thenReturn(nick)
        whenever(dataRepositoryMock.getUserByNick(nick)).thenReturn(null)

        var exceptionThrown = false
        try {
            presenter.resumeView()
        } catch (e: IllegalStateException) {
            exceptionThrown = true
            verify(sharedPreferencesManagerMock).getActualUserNick()
            verify(dataRepositoryMock).getUserByNick(eq(nick))
        }
        assertTrue(exceptionThrown)
    }

    @Test
    fun resumeView_expected() {
        val nick = "someNick"
        val user = User("John", "Smith", nick)
        whenever(sharedPreferencesManagerMock.getActualUserNick()).thenReturn(nick)
        whenever(dataRepositoryMock.getUserByNick(nick)).thenReturn(user)

        presenter.resumeView()

        verify(sharedPreferencesManagerMock).getActualUserNick()
        verify(dataRepositoryMock).getUserByNick(eq(nick))
        verify(viewMock).showUserName(eq(user.firstName))
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(sharedPreferencesManagerMock)
        verifyNoMoreInteractions(dataRepositoryMock)
        verifyNoMoreInteractions(viewMock)
    }
}
