package com.mvillasenor.bookfinder.dsl.actions

import com.mvillasenor.bookfinder.utils.Utils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals

open class ServerActions(val server: MockWebServer) : Actions {
    fun enqueueJson(jsonFile: String): Actions {
        server.enqueue(Utils.createResponse(jsonFile))
        return EndAction
    }

    fun enqueueError(): Actions {
        server.enqueue(MockResponse().setResponseCode(500))
        return EndAction
    }

    fun checkRequestCount(requestCount: Int): Actions {
        assertEquals(requestCount, server.requestCount)
        return EndAction
    }
}
