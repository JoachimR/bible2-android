package net.bible2

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

private val screens = listOf(AppScreen.Content, AppScreen.List, AppScreen.Info)

@Composable
fun AppBottomBar(navController: NavHostController) {
    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                label = { Text(stringResource(screen.resourceId)) },
                onClick = { navController.navigate(screen.route) },
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                selected = isScreenSelected(screen, navController),
            )
        }
    }
}

@Composable
private fun isScreenSelected(
    screen: AppScreen,
    navController: NavHostController
): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true
}
