package com.mvillasenor.bookfinder.extensions

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click as espressoClick

fun ViewInteraction.click(): Unit {
    this.perform(espressoClick())
}

fun ViewInteraction.enterText(text: String): Unit {
    this.perform(typeText(text), closeSoftKeyboard())
}
