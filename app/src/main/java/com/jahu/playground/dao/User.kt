package com.jahu.playground.dao

import com.orm.SugarRecord

data class User(
        val id: Long = 0,
        val firstName: String = "",
        val lastName: String = "",
        val nick: String = ""
) : SugarRecord<User>()
