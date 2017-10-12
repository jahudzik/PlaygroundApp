package com.jahu.playground.features.dashboard

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.jahu.playground.R

@SuppressWarnings("UseDataClass")
sealed class BottomNavigationItem(
        val id: Int,
        @StringRes val title: Int,
        @DrawableRes val icon: Int
) {

    companion object {
        const val QUIZ_SETUP = 0
        const val LEADERBOARD = 1
        const val SETTINGS = 2
    }

    class QuizSetupItem : BottomNavigationItem(QUIZ_SETUP, R.string.play, R.drawable.ic_setup_quiz)

    class LeaderboardItem : BottomNavigationItem(LEADERBOARD, R.string.leaderboard, R.drawable.ic_leaderboard)

    class SettingsItem : BottomNavigationItem(SETTINGS, R.string.settings, R.drawable.ic_settings)

    override fun equals(other: Any?): Boolean {
        return (other as? BottomNavigationItem)?.let { id == it.id } ?: false
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
