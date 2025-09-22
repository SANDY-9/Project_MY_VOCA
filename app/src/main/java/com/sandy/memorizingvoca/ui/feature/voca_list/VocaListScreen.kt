package com.sandy.memorizingvoca.ui.feature.voca_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaListRoute(
    day: Int,
    onItemClick: (Int) -> Unit,
) {
    VocaListScreen(
        onItemClick = onItemClick,
    )
}

@Composable
private fun VocaListScreen(
    onItemClick: (Int) -> Unit,
) {
    Text(
        text = "Voca List"
    )
}

@Composable
@Preview
private fun VocaListScreenPreview() {
    MemorizingVocaTheme {
        VocaListScreen(
            onItemClick = {}
        )
    }
}