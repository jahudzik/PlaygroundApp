package com.jahu.playground.features.matchers

import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class ChildViewMatcher(
        private val parentMatcher: Matcher<View>,
        private val childPosition: Int
) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("position $childPosition of parent ");
        parentMatcher.describeTo(description);
    }

    public override fun matchesSafely(view: View): Boolean {
        if (view.parent !is ViewGroup) return false
        val parent = view.parent as ViewGroup

        return (parentMatcher.matches(parent)
                && parent.childCount > childPosition
                && parent.getChildAt(childPosition) == view)
    }

}
