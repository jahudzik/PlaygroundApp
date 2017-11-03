package com.jahu.playground.repositories

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "playground-prefs"
private const val ACTUAL_USER_KEY = "actualUser"

class SharedPreferencesManager(
        context: Context
) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun setActualUserId(userId: Long) {
        sharedPreferences.edit().putLong(ACTUAL_USER_KEY, userId).apply()
    }

    fun getActualUserId(): Long {
        return sharedPreferences.getLong(ACTUAL_USER_KEY, -1L)
    }

}
