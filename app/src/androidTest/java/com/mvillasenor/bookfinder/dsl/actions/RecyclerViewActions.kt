package com.mvillasenor.bookfinder.dsl.actions

import android.view.View
import androidx.test.espresso.assertion.ViewAssertions
import com.mvillasenor.bookfinder.utils.CustomMatchers
import org.hamcrest.Matcher

open class RecyclerViewActions(matcher: () -> Matcher<View>) : ViewActions(matcher) {

    fun matchesItemCount(itemCount: Int): Actions {
        onView().check(ViewAssertions.matches(CustomMatchers.withItemCount(itemCount)))
        return EndAction
    }

}
