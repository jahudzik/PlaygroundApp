package com.jahu.playground.features.dashboard

import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DashboardPresenterTest {

    private lateinit var presenter: DashboardPresenter

    @Mock private lateinit var view: DashboardContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DashboardPresenter(view)
    }


    @Test
    fun createView_expected() {
        val expectedItems = listOf(
                BottomNavigationItem.QuizSetupItem(),
                BottomNavigationItem.LeaderboardItem(),
                BottomNavigationItem.SettingsItem()
        )

        presenter.createView()

        verify(view).showBottomNavigationBar(eq(expectedItems))
        verify(view).showQuizSetup()
    }

    @Test
    fun onNavigationItemSelected_quizSetup() {
        presenter.onNavigationItemSelected(BottomNavigationItem.QUIZ_SETUP)

        verify(view).showQuizSetup()
    }

    @Test
    fun onNavigationItemSelected_leaderboard() {
        presenter.onNavigationItemSelected(BottomNavigationItem.LEADERBOARD)

        verify(view).showLeaderboard()
    }

    @Test
    fun onNavigationItemSelected_settings() {
        presenter.onNavigationItemSelected(BottomNavigationItem.SETTINGS)

        verify(view).showSettings()
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
    }

}
