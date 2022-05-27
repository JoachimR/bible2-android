package net.bible2.presentation

sealed class Screen(val route: String) {
    object TwdListScreen: Screen("twd_list_screen")
    object TwdDetailScreen: Screen("twd_detail_screen")
}
