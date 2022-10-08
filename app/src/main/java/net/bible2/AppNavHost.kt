package net.bible2

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.bible2.common.Constants
import net.bible2.presentation.content.ContentScreen
import net.bible2.presentation.list.ListScreen

@Composable
fun AppNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.List.route,
        modifier = androidx.compose.ui.Modifier.padding(innerPadding)
    ) {
        composable(
            route = AppScreen.List.route
        ) {
            ListScreen(navController)
        }
        composable(
            route = AppScreen.Content.route +
                "/{${Constants.PARAM_BIBLE}}" +
                "/{${Constants.PARAM_YEAR}}",
            arguments = AppScreen.Content.arguments
        ) {
            ContentScreen(navController)
        }
        composable(
            route = AppScreen.Info.route
        ) {
            Text(text = "Info")
        }
    }
}
