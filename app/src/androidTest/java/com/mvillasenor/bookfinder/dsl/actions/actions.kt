package com.mvillasenor.bookfinder.dsl.actions

import androidx.test.espresso.action.ViewActions as EspressoActions

@DslMarker
annotation class ActionsMarker

@ActionsMarker
interface Actions

object EndAction : Actions
