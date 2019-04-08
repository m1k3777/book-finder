package com.mvillasenor.bookfinder.dsl.actions

import android.view.View
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

open class ViewActions(matcher: () -> Matcher<View>) : ScreenActions(matcher) {
    fun click(): Actions {
        onView().perform(ViewActions.click())
        return EndAction
    }

    fun matchesVisibility(visibility: ViewMatchers.Visibility): Actions {
        onView().check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility)))
        return EndAction
    }
}
