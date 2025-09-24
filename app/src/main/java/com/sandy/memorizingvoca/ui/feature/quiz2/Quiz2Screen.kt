package com.sandy.memorizingvoca.ui.feature.quiz2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun Quiz2Route(
    onNavigateBack: () -> Unit,
    onNavigateResult: (String) -> Unit,
) {
    Quiz2Screen(
        onNavigateBack = onNavigateBack,
    )
}

@Composable
private fun Quiz2Screen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun Quiz2ScreenPreview() {
    MemorizingVocaTheme {
        Quiz2Screen(
            onNavigateBack = {},
        )
    }
}
