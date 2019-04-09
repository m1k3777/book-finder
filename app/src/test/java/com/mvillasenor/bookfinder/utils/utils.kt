package com.mvillasenor.bookfinder.utils

import okhttp3.mockwebserver.MockResponse
import java.io.File

object Util {
    fun loadJson(path: String): String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    fun createResponse(path: String) = MockResponse()
        .setResponseCode(200)
        .setBody(loadJson(path))
}
