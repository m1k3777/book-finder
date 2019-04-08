package com.mvillasenor.bookfinder.search

import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.mvillasenor.bookfinder.dsl.robot
import com.mvillasenor.bookfinder.ui.search.MainActivity
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
class SearchTest : KoinTest {

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
        robot {
            inSearchScreen {
                inNoResultView {
                    isDisplayed()
                }
                inSearchResultsView {
                    matchesVisibility(Visibility.VISIBLE)
                }
            }
        }
    }

    @Test
    fun loadingStateIsShownCorrectly() {
        robot {
            inSearchScreen {
                inSearchField {
                    typeText("t")
                }
                inLoadingView {
                    matchesVisibility(Visibility.VISIBLE)
                }
            }
        }
    }

    @Test
    fun errorStateIsShownCorrectly() {
        robot {
            inServer(server) {
                enqueueError()
            }
            inSearchScreen {
                inSearchField {
                    typeText("t")
                }
                inErrorView {
                    matchesVisibility(Visibility.VISIBLE)
                }
            }
        }
    }

    @Test
    fun correctNumberOfItems() {
        robot {
            inServer(server) {
                enqueueJson("json/search_response.json")
            }
            inSearchScreen {
                inSearchField {
                    typeText("t")
                }
                inSearchResultsView {
                    matchesItemCount(2)
                }
            }
        }
    }

    @Test
    fun retriesOnError() {
        robot {
            inServer(server) {
                enqueueError()
                enqueueJson("json/search_response.json")
            }
            inSearchScreen {
                inSearchField {
                    typeText("t")
                }
                inErrorView {
                    matchesVisibility(Visibility.VISIBLE)
                }
                inRetryButton {
                    click()
                }
                inSearchResultsView {
                    matchesVisibility(Visibility.VISIBLE)
                    matchesItemCount(2)
                }
            }
            inServer(server) {
                matchesRequestCount(2)
            }
        }
    }
}
