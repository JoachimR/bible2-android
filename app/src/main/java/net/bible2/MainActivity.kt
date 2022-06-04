package net.bible2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import net.bible2.common.Constants
import net.bible2.presentation.AppScreen
import net.bible2.presentation.content.ContentScreen
import net.bible2.presentation.list.TwdListScreen
import net.bible2.ui.theme.Bible2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Bible2Theme {
                Surface {
                    AppScaffold()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppScaffold() {
    val navController = rememberNavController()
    Scaffold(
        topBar = createCenterAlignedTopAppBar(),
        bottomBar = createBottomBar(navController),
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppScreen.TwdList.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(
                    route = AppScreen.TwdList.route
                ) {
                    TwdListScreen(navController)
                }
                composable(
                    route = AppScreen.Content.route +
                        "/{${Constants.PARAM_BIBLE}}" +
                        "/{${Constants.PARAM_YEAR}}",
                    arguments = listOf(
                        navArgument(Constants.PARAM_BIBLE) { type = NavType.StringType },
                        navArgument(Constants.PARAM_YEAR) { type = NavType.IntType },
                    )
                ) {
                    ContentScreen()
                }
                composable(
                    route = AppScreen.Info.route
                ) {
                    Text(text = "Info")
                }
            }
        }
    )
}

private val bottomNavigationScreens = listOf(AppScreen.TwdList, AppScreen.Info)

private fun createBottomBar(navController: NavHostController): @Composable () -> Unit = {
    NavigationBar {
        bottomNavigationScreens.forEach { screen ->
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

private fun createCenterAlignedTopAppBar(): @Composable () -> Unit = {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        title = { Text(LocalContext.current.getString(R.string.app_name)) },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = LocalContext.current.getString(R.string.cd_share)
                )
            }
            AppDropdown()
        }
    )
}

@Composable
private fun AppDropdown() {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = LocalContext.current.getString(R.string.cd_more)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Settings") },
                onClick = {
                    Toast.makeText(context, "Settings", Toast.LENGTH_SHORT)
                        .show()
                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Settings,
                        contentDescription = null
                    )
                }
            )
            MenuDefaults.Divider()
            DropdownMenuItem(
                text = { Text("Info") },
                onClick = {
                    Toast.makeText(context, "Info", Toast.LENGTH_SHORT)
                        .show()
                },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Info,
                        contentDescription = null
                    )
                },
            )
        }
    }
}
