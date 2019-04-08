package com.mvillasenor.bookfinder.dsl.actions

import androidx.annotation.IdRes
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.action.ViewActions as EspressoActions


@DslMarker
annotation class ActionsMarker

@ActionsMarker
interface Actions

object EndAction : Actions

internal fun idMatcher(@IdRes resId: Int) = {
    ViewMatchers.withId(resId)
}
