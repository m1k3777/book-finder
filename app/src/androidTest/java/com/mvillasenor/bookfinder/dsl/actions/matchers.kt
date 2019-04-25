package com.mvillasenor.bookfinder.dsl.actions

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers

internal fun idMatcher(@IdRes resId: Int) = {
    ViewMatchers.withId(resId)
}
