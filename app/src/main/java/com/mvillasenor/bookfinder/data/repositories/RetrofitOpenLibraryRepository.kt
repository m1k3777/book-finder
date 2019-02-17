package com.mvillasenor.bookfinder.data.repositories

import com.mvillasenor.bookfinder.data.SearchResult
import com.mvillasenor.bookfinder.data.retrofit.OpenLibraryClient
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit

class RetrofitOpenLibraryRepository(retrofit: Retrofit) : OpenLibraryRepository {

    private val openLibraryClient: OpenLibraryClient = retrofit.create(OpenLibraryClient::class.java)

    override fun search(query: String, page: Int?): Deferred<SearchResult> = openLibraryClient.searchAsync(query, page)

}