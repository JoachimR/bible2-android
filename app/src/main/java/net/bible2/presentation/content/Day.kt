package net.bible2.presentation.content

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import net.bible2.DayOfYear
import net.bible2.domain.model.Parol
import net.bible2.domain.model.TheWord
import net.bible2.util.textForDayOfYear

@Composable
fun Day(item: TheWord?, dayOfYear: DayOfYear) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            DateHeader(dayOfYear)

            if (item == null) {
                Text("No data found for this day")
                return
            }

            TextCard(item.parol0)
            Spacer(modifier = Modifier.height(16.dp))
            TextCard(item.parol1)
        }
    }
}

@Composable
private fun DateHeader(dayOfYear: DayOfYear) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = textForDayOfYear(LocalContext.current, dayOfYear),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun TextCard(parol: Parol) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (parol.intro != null) {
                Text(
                    text = parol.intro,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = parol.text,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.body1,
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
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
