package com.jahu.playground


import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.jahu.playground.features.chooseuser.ChooseUserActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class FunctionalTest : BaseTest() {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(ChooseUserActivity::class.java)

    @Test
    fun userCreation() {
        checkText(R.id.noUsersMessage, R.string.no_users)

        // Add new user
        performClick(R.id.addUserButton)
        performTextReplace(R.id.firstNameEditText, "John")
        performTextReplace(R.id.lastNameEditText, "Smith")
        performTextReplace(R.id.nickEditText, "johnny")
        performClick(R.id.confirmButton)

        checkRecyclerViewItem(R.id.usersRecyclerView, 0, "johnny")

        performRecyclerViewItemClick(R.id.usersRecyclerView, "johnny")
    }

}
