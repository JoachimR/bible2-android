package net.bible2

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import net.bible2.common.Constants

typealias CreateRouteWithArguments = () -> String

sealed class AppScreen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    val arguments: kotlin.collections.List<NamedNavArgument> = emptyList(),
    val createRouteForNavigation: CreateRouteWithArguments = { route }
) {
    object Content :
        AppScreen(
            route = "app_screen_content",
            resourceId = R.string.screen_content,
            icon = Icons.Filled.Article,
            arguments = listOf(
                navArgument(Constants.PARAM_BIBLE) {
                    type = NavType.StringType
                },
                navArgument(Constants.PARAM_YEAR) {
                    type = NavType.IntType
                }
            ),
            createRouteForNavigation = {
                "app_screen_content" +
                    "/{${Constants.PARAM_BIBLE}}" +
                    "/{${Constants.PARAM_YEAR}}"
            }
        )

    object List : AppScreen(
        route = "app_screen_list",
        resourceId = R.string.screen_list,
        icon = Icons.Filled.List
    )

    object Info : AppScreen(
        route = "app_screen_info",
        resourceId = R.string.screen_info,
        icon = Icons.Filled.Info
    )
}
