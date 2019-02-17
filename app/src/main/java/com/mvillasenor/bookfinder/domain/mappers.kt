package com.mvillasenor.bookfinder.domain

import com.mvillasenor.bookfinder.data.SearchResult
import com.mvillasenor.bookfinder.data.SearchResultItem
import com.mvillasenor.bookfinder.domain.SearchResult as DomainSearchResult

fun SearchResult.toEntity(currentPage: Int) = DomainSearchResult(
    currentPage,
    this.start + this.docs.size < this.numFound,
    this.docs.map { it.toEntity() }
)


fun SearchResultItem.toEntity(): Book = Book(
    this.key,
    this.title,
    this.authorName ?: emptyList(),
    this.coverId?.let { "http://covers.openlibrary.org/b/ID/$it-M.jpg" },
    this.publishYear
)
