package com.mvillasenor.bookfinder.data.retrofit

import com.mvillasenor.bookfinder.data.SearchResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryClient {

    @GET("search.json")
    fun searchAsync(@Query("q") query: String, @Query("page") page: Int?): Deferred<SearchResult>

}