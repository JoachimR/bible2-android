@file:OptIn(ExperimentalMaterial3Api::class)

package net.bible2.presentation.content.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import net.bible2.domain.model.Parol
import net.bible2.domain.model.TheWord
import net.bible2.presentation.content.ScrollingColumn

class TheWordPreviewParameterProvider : PreviewParameterProvider<TheWord> {
    override val values = sequenceOf(
        TheWord(
            dayOfYear = 22,
            parol0 = Parol(
                intro = "Some intro 0",
                text = "Some text 0 ".repeat(200),
                ref = "Some ref 0"
            ),
            parol1 = Parol(
                intro = "Some intro 1",
                text = "Some text 1 ".repeat(200),
                ref = "Some ref 1"
            )
        )
    )
}

@Preview
@Composable
fun TheWordDisplay(@PreviewParameter(TheWordPreviewParameterProvider::class) theWord: TheWord) {
    ScrollingColumn {
        ContentDateHeader(theWord.dayOfYear)
        TextCard(theWord.parol0)
        Spacer(modifier = Modifier.height(16.dp))
        TextCard(theWord.parol1)
    }
}

@Composable
private fun TextCard(parol: Parol) {
    ElevatedCard(
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (parol.intro != null) {
                Text(
                    text = parol.intro,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Thin,
                    fontStyle = FontStyle.Italic
                )
            }
            Text(
                text = parol.text,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(top = 8.dp))

            ) {
                Text(
                    text = parol.ref,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
