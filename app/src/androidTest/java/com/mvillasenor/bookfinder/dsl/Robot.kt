package com.mvillasenor.bookfinder.dsl

import com.mvillasenor.bookfinder.dsl.actions.Actions
import com.mvillasenor.bookfinder.dsl.actions.ServerActions
import com.mvillasenor.bookfinder.dsl.screens.SearchScreen
import okhttp3.mockwebserver.MockWebServer

class Robot: Actions {
    fun inSearchScreen(action: SearchScreen.() -> Actions) = SearchScreen().action()

    fun inServer(server: MockWebServer, action: ServerActions.() -> Actions) = ServerActions(
        server
    ).action()
}

fun robot(action: Robot.() -> Actions) {
    Robot().action()
}
