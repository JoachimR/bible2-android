package net.bible2.presentation.content

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import net.bible2.DayOfYear
import net.bible2.R

class DayOfYearParameterProvider : PreviewParameterProvider<DayOfYear> {
    override val values = sequenceOf(22)
}

@Preview
@Composable
fun NotFoundTheWordDisplay(
    @PreviewParameter(DayOfYearParameterProvider::class) dayOfYear: DayOfYear
) {
    ScrollingColumn {
        DateHeader(dayOfYear)
        Text(
            text = stringResource(R.string.day_content_not_found),
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            textAlign = TextAlign.Center,
        )
    }
}
