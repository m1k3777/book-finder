package com.mvillasenor.bookfinder.domain.usecases

import com.mvillasenor.bookfinder.data.repositories.OpenLibraryRepository
import com.mvillasenor.bookfinder.domain.SearchResult
import com.mvillasenor.bookfinder.domain.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class SearchBook(
    private val openLibraryRepository: OpenLibraryRepository,
    private val ioDispatcher: CoroutineDispatcher
) : (String, Int) -> Deferred<SearchResult> {

    override fun invoke(query: String, page: Int): Deferred<SearchResult> =
        CoroutineScope(ioDispatcher).async {
            openLibraryRepository
                .search(query, page)
                .await()
                .toEntity(page)
        }

}