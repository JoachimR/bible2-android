package net.bible2.presentation.content.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import net.bible2.DaysInAYear
import net.bible2.domain.model.TheWordFileContent
import net.bible2.util.currentDayOfYear

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ContentLoaded(content: TheWordFileContent) {
    Box(modifier = Modifier.fillMaxSize()) {
        assert(content.items.size == DaysInAYear)

        val pagerState = rememberPagerState()
        HorizontalPager(count = DaysInAYear, state = pagerState) { index ->
            TheWordDisplay(theWord = content.items[index])
        }

        val key = currentDayOfYear()
        LaunchedEffect(key1 = key, block = {
            pagerState.scrollToPage(key)
        })
    }
}
