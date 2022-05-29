package net.bible2.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import net.bible2.DaysInAYear
import net.bible2.util.currentDayOfYear

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentScreen(
    viewModel: ContentViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
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
            } else {
                if (state.theWordFileContent == null) {
                    Text(
                        text = "No content found",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                } else {
                    val pagerState = rememberPagerState()
                    HorizontalPager(count = DaysInAYear, state = pagerState) { index ->
                        val dayOfYear = index + 1
                        Day(viewModel.getForDay(dayOfYear = dayOfYear), dayOfYear = dayOfYear)
                    }
                    val key = currentDayOfYear()
                    LaunchedEffect(key1 = key, block = {
                        pagerState.scrollToPage(key)
                    })
                }
            }
        }
    }
}
