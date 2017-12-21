package com.jahu.playground.features.leaderboard

import com.jahu.playground.data.LeaderboardEntry
import com.jahu.playground.usecases.games.GetLeaderboardEntriesUseCase
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LeaderboardPresenterTest {

    private lateinit var presenter: LeaderboardPresenter

    @Mock private lateinit var view: LeaderboardContract.View

    @Mock private lateinit var getLeaderboardEntriesUseCase: GetLeaderboardEntriesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeaderboardPresenter(view, getLeaderboardEntriesUseCase)
    }

    @Test
    fun resumeView_expected() {
        val entries = mock<List<LeaderboardEntry>>()
        whenever(getLeaderboardEntriesUseCase.execute()).thenReturn(entries)

        presenter.resumeView()

        verify(getLeaderboardEntriesUseCase).execute()
        verify(view).showLeaderboardEntries(entries)
    }

    @After
    fun tearDown() {
        verifyNoMoreInteractions(view)
        verifyNoMoreInteractions(getLeaderboardEntriesUseCase)
    }
}
