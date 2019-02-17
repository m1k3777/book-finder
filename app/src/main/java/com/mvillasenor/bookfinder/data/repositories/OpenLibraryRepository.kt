package com.mvillasenor.bookfinder.data.repositories

import com.mvillasenor.bookfinder.data.SearchResult
import kotlinx.coroutines.Deferred

interface OpenLibraryRepository {

    fun search(query: String, page: Int? = null): Deferred<SearchResult>

}