package net.bible2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            }
        }
    )
}

private fun createCenterAlignedTopAppBar(): @Composable () -> Unit = {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        title = { Text(LocalContext.current.getString(R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = LocalContext.current.getString(R.string.cd_menu)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = LocalContext.current.getString(R.string.cd_share)
                )
            }
        }
    )
}
