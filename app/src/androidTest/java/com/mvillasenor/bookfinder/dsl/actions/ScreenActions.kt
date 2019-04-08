package com.mvillasenor.bookfinder.dsl.actions

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

abstract class ScreenActions(protected val matcher: () -> Matcher<View>) :
    Actions {

    fun isDisplayed(): Actions {
        onView().check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return EndAction
    }

    protected fun onView() = Espresso.onView(Matchers.allOf(matcher(), ViewMatchers.isDisplayed()))
}
