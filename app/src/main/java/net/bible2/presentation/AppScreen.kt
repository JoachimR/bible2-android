package net.bible2.presentation

sealed class AppScreen(val route: String) {
    object TwdList : AppScreen("app_screen_twd_list")
    object Content : AppScreen("app_screen_content")
}
