package net.bible2

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold() {
    val navController: NavHostController = rememberNavController()
    Scaffold(
        topBar = { AppCenterAlignedTopAppBar() },
        bottomBar = { AppBottomBar(navController) },
        content = { innerPadding ->
            AppNavHost(navController, innerPadding)
        }
    )
}
