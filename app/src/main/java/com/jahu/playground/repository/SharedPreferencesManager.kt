package com.jahu.playground.repository

import android.content.Context
import android.content.SharedPreferences

private const val PREFS_NAME = "playground-prefs"
private const val ACTUAL_USER_KEY = "actualUser"

class SharedPreferencesManager(
        context: Context
) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun setActualUserNick(nick: String) {
        sharedPreferences.edit().putString(ACTUAL_USER_KEY, nick).apply()
    }

    fun getActualUserNick(): String? {
        return sharedPreferences.getString(ACTUAL_USER_KEY, null)
    }

}
