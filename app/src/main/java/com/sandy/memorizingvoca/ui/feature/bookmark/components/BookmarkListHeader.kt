package com.sandy.memorizingvoca.ui.feature.bookmark.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
internal fun BookmarkListHeader(
    itemCount: Int,
    currentQueryText: String,
    onAllDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$currentQueryText 목록($itemCount)",
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                modifier = modifier.noRippleClickable(
                    onClick = onAllDeleteClick
                ),
                text = "전체 삭제",
                color = Pink100,
                fontWeight = FontWeight.Medium,
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        HorizontalDivider(color = Gray30)
    }
}

@Preview
@Composable
private fun BookmarkListHeaderPreview() {
    MemorizingVocaTheme {
        BookmarkListHeader(
            itemCount = 3,
            currentQueryText = "전체",
            onAllDeleteClick = {},
        )
    }
}
