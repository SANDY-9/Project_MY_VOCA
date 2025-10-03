package com.sandy.memorizingvoca.ui.feature.bookmark.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun BookmarkDayStickyHeader(
    day: Int,
    count: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().background(
            color = MaterialTheme.colorScheme.tertiary,
        ).padding(
            horizontal = 16.dp,
            vertical = 4.dp,
        )
    ) {
        Text(
            text = "DAY " + String.format("%02d", day),
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = "${count}개",
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Preview
@Composable
private fun BookmarkDayStickyHeaderPreview() {
    MemorizingVocaTheme {
        BookmarkDayStickyHeader(
            day = 5,
            count = 3,
        )
    }
}
