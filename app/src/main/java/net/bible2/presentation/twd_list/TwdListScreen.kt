package net.bible2.presentation.twd_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import net.bible2.domain.model.Twd
import net.bible2.presentation.twd_list.components.TwdListItem
import java.util.*

@Composable
fun TwdListScreen(
    navController: NavController,
    viewModel: TwdListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sortedItemsForCurrentYear(state.twds)) { twd ->
                TwdListItem(
                    item = twd,
                    onItemClick = {
//                        navController.navigate(Screen.TwdDetailScreen.route + "/${twd.bible}/${twd.year}/")
                    }
                )
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

private fun sortedItemsForCurrentYear(items: List<Twd>): List<Twd> =
    with(Calendar.getInstance()[Calendar.YEAR]) {
        items.filter { it.year == this }
            .sortedWith(compareBy({ it.lang }, { it.title }))
    }