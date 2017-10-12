package com.jahu.playground.features.dashboard

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BottomNavigationItemTest {

    @Test
    fun equals_same() {
        val item1 = BottomNavigationItem.LeaderboardItem()
        val item2 = BottomNavigationItem.LeaderboardItem()
        assertTrue(item1.equals(item2))
    }

    @Test
    fun equals_differentItems() {
        val item1 = BottomNavigationItem.LeaderboardItem()
        val item2 = BottomNavigationItem.SettingsItem()
        assertFalse(item1.equals(item2))
    }

    @Test
    fun equals_null() {
        val item1 = BottomNavigationItem.LeaderboardItem()
        assertFalse(item1.equals(null))
    }

    @Test
    fun equals_differentClass() {
        val item1 = BottomNavigationItem.LeaderboardItem()
        val item2 = "string"
        assertFalse(item1.equals(item2))
    }
}
