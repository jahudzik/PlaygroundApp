package com.jahu.playground


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jahu.playground.features.chooseuser.ChooseUserActivity
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
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

        checkRecyclerViewItem(R.id.usersRecyclerView, 0, "johnny")

        performRecyclerViewItemClick(R.id.usersRecyclerView, "johnny")
    }

    private fun performClick(viewId: Int) {
        onView(allOf(withId(viewId), isDisplayed()))
                .perform(click())
    }

    private fun performRecyclerViewItemClick(viewId: Int, itemValue: String) {
        onView(allOf(withId(viewId), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                        hasDescendant(withText(containsString(itemValue))),
                        click()
                ))
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

    private fun checkRecyclerViewItem(viewId: Int, expectedPosition: Int, expectedValue: String) {
        onView(allOf(withId(viewId), isDisplayed()))
                .check(matches(atPosition(expectedPosition, hasDescendant(withText(expectedValue)))))
    }

    // https://stackoverflow.com/a/34795431/8490964
    private fun atPosition(position: Int, itemMatcher: Matcher<View>): BaseMatcher<View> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position) ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}
