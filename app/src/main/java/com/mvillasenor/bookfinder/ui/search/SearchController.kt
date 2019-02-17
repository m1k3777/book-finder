package com.mvillasenor.bookfinder.ui.search

import com.airbnb.epoxy.TypedEpoxyController
import com.mvillasenor.bookfinder.domain.Book

class SearchController : TypedEpoxyController<List<Book>>() {
    override fun buildModels(books: List<Book>?) {
        books?.forEach {
            searchResult {
                id(it.key)
                title(it.title)
                cover(it.coverUrl)
                author(it.authorName.fold("") { a, b -> "$a $b," })
            }
        }
    }
}