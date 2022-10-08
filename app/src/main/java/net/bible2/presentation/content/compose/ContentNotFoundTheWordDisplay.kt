package net.bible2.presentation.content.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import net.bible2.DayOfYear
import net.bible2.R
import net.bible2.presentation.content.ScrollingColumn

@Composable
fun NotFoundTheWordDisplay(
    dayOfYear: DayOfYear,
    navController: NavHostController
) {
    ScrollingColumn {
        ContentDateHeader(dayOfYear)
        Text(
            text = stringResource(R.string.day_content_not_found),
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            textAlign = TextAlign.Center,
        )
        ContentGoToListButton(navController)
    }
}
