package com.sandy.memorizingvoca.ui.feature.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun BookmarkRoute() {
    BookmarkScreen()
}

@Composable
private fun BookmarkScreen(
    modifier: Modifier = Modifier,
) {
    Column {
        Text("북마크")
    }
}

@Preview
@Composable
private fun BookmarkScreenPreview() {
    MemorizingVocaTheme {
        BookmarkScreen(
        )
    }
}
