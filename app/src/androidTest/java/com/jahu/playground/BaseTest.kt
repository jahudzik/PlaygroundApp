package com.jahu.playground

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jahu.playground.features.matchers.ChildViewMatcher
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import java.io.File

abstract class BaseTest {

    protected fun performClick(viewId: Int) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(viewId), ViewMatchers.isDisplayed()))
                .perform(ViewActions.click())
    }

    protected fun performClick(viewId: Int, vararg childPositions: Int) {
        var viewMatcher = ViewMatchers.withId(viewId)
        for (childPosition in childPositions) {
            viewMatcher = ChildViewMatcher(viewMatcher, childPosition)
        }
        Espresso.onView(Matchers.allOf(viewMatcher, ViewMatchers.isDisplayed()))
                .perform(ViewActions.click())
    }

    protected fun performRecyclerViewItemClick(viewId: Int, itemValue: String) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(viewId), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                        ViewMatchers.hasDescendant(ViewMatchers.withText(Matchers.containsString(itemValue))),
                        ViewActions.click()
                ))
    }

    protected fun performTextReplace(viewId: Int, value: String) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(viewId), ViewMatchers.isDisplayed()))
                .perform(ViewActions.replaceText(value), ViewActions.closeSoftKeyboard())
    }

    protected fun checkText(viewId: Int, expectedValue: String) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(viewId), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedValue)))
    }

    protected fun checkText(viewId: Int, expectedResourceId: Int) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(viewId), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText(expectedResourceId)))
    }

    protected fun checkRecyclerViewItems(viewId: Int, vararg expectedValues: String) {
        expectedValues.forEachIndexed { index, expectedValue ->
            checkRecyclerViewItem(viewId, index, expectedValue)
        }
    }

    protected fun checkRecyclerViewItem(viewId: Int, expectedPosition: Int, expectedValue: String) {
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(viewId), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(atPosition(expectedPosition, ViewMatchers.hasDescendant(ViewMatchers.withText(expectedValue)))))
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

    // https://stackoverflow.com/a/37606822/8490964
    protected fun clearSharedPreferences() {
        val root = InstrumentationRegistry.getTargetContext().filesDir.parentFile
        val sharedPreferencesFileNames = File(root, "shared_prefs").list()
        sharedPreferencesFileNames?.let {
            for (fileName in it) {
                InstrumentationRegistry.getTargetContext().getSharedPreferences(fileName.replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit()
            }
        }
    }

}
