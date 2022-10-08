package net.bible2.presentation.content

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.bible2.presentation.content.compose.ContentBibleMissing
import net.bible2.presentation.content.compose.ContentError
import net.bible2.presentation.content.compose.ContentLoaded
import net.bible2.presentation.content.compose.ContentLoadingState

@Composable
fun ContentScreen(
    navController: NavHostController,
    viewModel: ContentViewModel = hiltViewModel(),
) {
    when (val state = viewModel.state.value) {
        is ContentState.Loading -> {
            ContentLoadingState()
        }
        is ContentState.ErrorLoading -> {
            ContentError(state.message)
        }
        is ContentState.BibleMissing -> {
            ContentBibleMissing(navController)
        }
        is ContentState.Loaded -> {
            ContentLoaded(state.content)
        }
    }
}
