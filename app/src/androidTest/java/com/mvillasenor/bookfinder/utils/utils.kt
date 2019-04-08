package com.mvillasenor.bookfinder.utils

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.MockResponse

object Utils {
    fun loadJson(path: String): String {
        val context = InstrumentationRegistry.getInstrumentation().context
        val stream = context.getResources().getAssets().open(path)
        val reader = stream.bufferedReader()
        val stringBuilder = StringBuilder()
        reader.lines().forEach {
            stringBuilder.append(it)
        }
        return stringBuilder.toString()
    }

    fun createResponse(path: String) = MockResponse()
        .setResponseCode(200)
        .setBody(loadJson(path))
}
