package com.sandy.memorizingvoca.ui.feature.voca_fullscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaFullScreenRoute(
    onNavigateBack: () -> Unit,
) {
    VocaFullScreen()
}

@Composable
private fun VocaFullScreen(
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun VocaFullScreenPreview() {
    MemorizingVocaTheme {
        VocaFullScreen(
        )
    }
}
