package com.mvillasenor.bookfinder.dsl.screens

import com.mvillasenor.bookfinder.R
import com.mvillasenor.bookfinder.dsl.actions.*

class SearchScreen : ScreenActions(idMatcher(R.id.searchScreen)) {

    fun inSearchResultsView(action: RecyclerViewActions.() -> Actions) =
        RecyclerViewActions(idMatcher(R.id.searchResults)).action()

    fun inNoResultView(action: ViewActions.() -> Actions) =
        ViewActions(idMatcher(R.id.noResults)).action()

    fun inErrorView(action: ViewActions.() -> Actions) =
        ViewActions(idMatcher(R.id.errorView)).action()

    fun inLoadingView(action: ViewActions.() -> Actions) =
        ViewActions(idMatcher(R.id.loading)).action()

    fun inRetryButton(action: ViewActions.() -> Actions) =
        ViewActions(idMatcher(R.id.retry)).action()

    fun inSearchField(action: EditTextActions.() -> Actions) =
        EditTextActions(idMatcher(R.id.searchText)).action()
}
