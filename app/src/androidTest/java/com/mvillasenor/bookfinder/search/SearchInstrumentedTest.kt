package com.mvillasenor.bookfinder.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mvillasenor.bookfinder.R
import com.mvillasenor.bookfinder.Utils
import com.mvillasenor.bookfinder.extensions.click
import com.mvillasenor.bookfinder.ui.search.MainActivity
import com.mvillasenor.bookfinder.utils.CustomMatchers.Companion.withItemCount
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SearchInstrumentedTest : KoinTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
        server.start(8888)
    }

    @After
    fun teardown() {
        server.shutdown()
    }

    @Test
    fun emptyStateOnStartup() {
        onView(withId(R.id.noResults)).check(matches(isDisplayed()))
        searchPageObject.searchResults.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun loadingStateIsShownCorrectly() {
        searchPageObject.performSearch("t")
        searchPageObject.loadingView.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun errorStateIsShownCorrectly() {
        server.enqueue(MockResponse().setResponseCode(500))
        searchPageObject.performSearch("t")
        searchPageObject.errorView.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun correctNumberOfItems() {
        server.enqueue(Utils.createResponse("json/search_response.json"))
        searchPageObject.performSearch("t")
        searchPageObject.searchResults.check(matches(withItemCount(2)))
    }

    @Test
    fun retriesOnError() {
        server.enqueue(MockResponse().setResponseCode(500))

        searchPageObject.performSearch("t")
        searchPageObject.errorView.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        server.enqueue(Utils.createResponse("json/search_response.json"))

        searchPageObject.retry.click()

        searchPageObject.searchResults.check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        searchPageObject.searchResults.check(matches(withItemCount(2)))

        assertEquals(2, server.requestCount)
    }
}
