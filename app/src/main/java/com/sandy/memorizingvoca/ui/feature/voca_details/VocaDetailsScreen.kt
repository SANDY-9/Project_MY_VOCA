package com.sandy.memorizingvoca.ui.feature.voca_details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaDetailsRoute(
    onNavigateBack: () -> Unit,
) {
    VocaDetailsScreen()
}
@Composable
private fun VocaDetailsScreen() {

}

@Composable
@Preview
private fun VocaDetailsScreenPreview() {
    MemorizingVocaTheme {
        VocaDetailsScreen()
    }
}