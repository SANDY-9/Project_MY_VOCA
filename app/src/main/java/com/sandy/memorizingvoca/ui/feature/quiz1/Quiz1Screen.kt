package com.sandy.memorizingvoca.ui.feature.quiz1

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun Quiz1Route(
    onNavigateBack: () -> Unit,
) {
    Quiz1Screen(
    )
}

@Composable
private fun Quiz1Screen(
    modifier: Modifier = Modifier,
) {

}

@Composable
@Preview
private fun Quiz1ScreenPreview() {
    MemorizingVocaTheme {
       Quiz1Screen()
    }
}