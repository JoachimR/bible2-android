package net.bible2.presentation.content.compose

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import net.bible2.AppScreen
import net.bible2.R

@Composable
fun ContentGoToListButton(navController: NavHostController) {
    Button(onClick = {
        navController.navigate(AppScreen.List.route)
    }) {
        Text(
            text = stringResource(R.string.button_go_to_list),
            color = MaterialTheme.colorScheme.primary
        )
    }
}
