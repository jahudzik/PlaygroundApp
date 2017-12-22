package com.jahu.playground


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.jahu.playground.features.chooseuser.ChooseUserActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FunctionalTest {

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
        checkText(R.id.userNameTextView, "johnny")
    }

    private fun performClick(viewId: Int) {
        onView(allOf(withId(viewId), isDisplayed()))
                .perform(click())
    }

    private fun performTextReplace(viewId: Int, value: String) {
        onView(allOf(withId(viewId), isDisplayed()))
                .perform(replaceText(value), closeSoftKeyboard())
    }

    private fun checkText(viewId: Int, expectedValue: String) {
        onView(allOf(withId(viewId), isDisplayed()))
                .check(matches(withText(expectedValue)))
    }

    private fun checkText(viewId: Int, expectedResourceId: Int) {
        onView(allOf(withId(viewId), isDisplayed()))
                .check(matches(withText(expectedResourceId)))
    }

}
