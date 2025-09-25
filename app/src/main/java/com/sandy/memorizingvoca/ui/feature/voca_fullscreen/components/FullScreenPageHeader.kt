package com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
internal fun FullScreenPageHeader(
    page: Int,
    totalPage: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(
            vertical = 4.dp,
            horizontal = 16.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Page ",
            color = Color.DarkGray,
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = "$page",
            fontWeight = FontWeight.Bold,
            color = Pink100,
        )
        Text(
            text = " / ",
            color = Color.Gray,
        )
        Text(
            text = "$totalPage",
            fontWeight = FontWeight.Medium,
        )
    }
}

@Preview
@Composable
private fun FullScreenFooterPreview() {
    MemorizingVocaTheme {
        FullScreenPageHeader(
            page = 1,
            totalPage = 6,
        )
    }
}
