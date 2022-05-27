package net.bible2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                // test
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AppScreen.TwdList.route
                    ) {
                        composable(
                            route = AppScreen.TwdList.route
                        ) {
                            TwdListScreen(navController)
                        }
                        composable(
                            route = "${AppScreen.Content.route}/{${Constants.PARAM_URL}}"
                        ) {
                            ContentScreen()
                        }
                    }
                }
            }
        }
    }
}