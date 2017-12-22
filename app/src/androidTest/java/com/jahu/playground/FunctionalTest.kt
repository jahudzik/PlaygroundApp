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

        // Add some users
        addNewUser("John", "Smith", "johnny")
        addNewUser("Margaret", "Brown", "maggie")
        addNewUser("Adam", "Jones", "cleo")
        checkRecyclerViewItems(R.id.usersRecyclerView, "cleo", "johnny", "maggie")

        // Choose user and navigate to the app
        performRecyclerViewItemClick(R.id.usersRecyclerView, "johnny")
    }

    private fun addNewUser(firstName: String, lastName: String, nick: String) {
        performClick(R.id.addUserButton)
        performTextReplace(R.id.firstNameEditText, firstName)
        performTextReplace(R.id.lastNameEditText, lastName)
        performTextReplace(R.id.nickEditText, nick)
        performClick(R.id.confirmButton)
    }

}
