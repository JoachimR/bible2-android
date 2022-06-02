package net.bible2.presentation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Today
import androidx.compose.ui.graphics.vector.ImageVector
import net.bible2.R

sealed class AppScreen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object TwdList : AppScreen("app_screen_twd_list", R.string.screen_today, Icons.Filled.Today)
    object Content :
        AppScreen("app_screen_content", R.string.screen_content, Icons.Filled.DateRange)

    object Info : AppScreen("app_screen_info", R.string.screen_info, Icons.Filled.Info)
}
