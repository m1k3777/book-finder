package com.mvillasenor.bookfinder.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.mvillasenor.bookfinder.R
import com.mvillasenor.bookfinder.extensions.enterText

object searchPageObject {

    val searchText = onView(withId(R.id.searchText))
    val searchResults = onView(withId(R.id.searchResults))
    val loadingView = onView(withId(R.id.loading))
    val errorView = onView(withId(R.id.errorView))
    val retry = onView(withId(R.id.retry))

    fun performSearch(query: String) {
        searchText.enterText(query)
    }

}
