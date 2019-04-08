package com.mvillasenor.bookfinder.dsl.actions

import android.view.View
import org.hamcrest.Matcher

open class EditTextActions(matcher: () -> Matcher<View>) : ViewActions(matcher) {
    fun typeText(text: String): Actions {
        onView()
            .perform(androidx.test.espresso.action.ViewActions.click())
            .perform(androidx.test.espresso.action.ViewActions.typeText(text))
            .perform(androidx.test.espresso.action.ViewActions.closeSoftKeyboard())
        return EndAction
    }
}
