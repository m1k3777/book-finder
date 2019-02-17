package com.mvillasenor.bookfinder.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeWith(lifecycleOwner: LifecycleOwner, onValue: (T) -> Unit) {
    this.observe(lifecycleOwner, Observer { onValue(it) })
}