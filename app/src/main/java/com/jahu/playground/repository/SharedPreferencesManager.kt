package com.jahu.playground.repository

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(
        context: Context
) {

    companion object {
        private val PREFS_NAME = "playground-prefs"
        private val ACTUAL_USER_KEY = "actualUser"
    }

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
    }

    fun setActualUserNick(nick: String) {
        sharedPreferences.edit().putString(ACTUAL_USER_KEY, nick).apply()
    }

    fun getActualUserNick(): String? {
        return sharedPreferences.getString(ACTUAL_USER_KEY, null)
    }

}
