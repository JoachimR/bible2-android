package net.bible2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.bible2.presentation.Screen
import net.bible2.presentation.twd_list.TwdListScreen
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
                        startDestination = Screen.TwdListScreen.route
                    ) {
                        composable(
                            route = Screen.TwdListScreen.route
                        ) {
                            TwdListScreen(navController)
                        }
//                        composable(
//                            route = Screen.TwdDetailScreen.route + "/{bible}" + "/{year}"
//                        ) {
//                           TwdDetailScreen(navController)
//                        }
                    }
                }
            }
        }
    }
}