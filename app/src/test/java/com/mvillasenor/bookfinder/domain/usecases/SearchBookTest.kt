package com.mvillasenor.bookfinder.domain.usecases

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mvillasenor.bookfinder.data.repositories.RetrofitOpenLibraryRepository
import com.mvillasenor.bookfinder.utils.Util.createResponse
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertFailsWith


class SearchBookTest {

    private val server = MockWebServer()
    lateinit var searchBook: SearchBook

    @Before
    fun setup() {
        server.start()
        val serverUrl = server.url("/")

        val retrofit = Retrofit.Builder()
            .baseUrl(serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        val repository = RetrofitOpenLibraryRepository(retrofit)
        searchBook = SearchBook(repository, Dispatchers.Default)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test
    fun `use case is successful`() = runBlocking {
        server.enqueue(createResponse("json/search_response.json"))
        val result = searchBook("q", 1).await()

        assertEquals(2, result.books.size)
        assertFalse(result.hasMore)
        assertEquals("Book 1", result.books[0].title)
    }

    @Test
    fun `service is only called once`() = runBlocking {
        server.enqueue(createResponse("json/search_response.json"))
        searchBook("q", 1).await()

        assertEquals(1, server.requestCount)
    }

    @Test
    fun `service is only with the right params`() = runBlocking {
        server.enqueue(createResponse("json/search_response.json"))
        searchBook("my search", 1).await()

        val request = server.takeRequest()

        assertEquals(setOf("q", "page"), request.requestUrl.queryParameterNames())
        assertEquals("my search", request.requestUrl.queryParameter("q"))
        assertEquals("1", request.requestUrl.queryParameter("page"))
    }

    @Test
    fun `service responds with an error`()  {
        server.enqueue(MockResponse().setResponseCode(500))
        assertFailsWith<HttpException> {
            runBlocking {
                searchBook("my search", 1).await()
            }
        }
    }
}
