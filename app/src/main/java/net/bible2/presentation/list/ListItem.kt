package net.bible2.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import net.bible2.domain.model.Twd

@Composable
fun ListItem(
    item: Twd,
    onItemClick: (Twd) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "[${item.lang}]",
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = item.title,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
