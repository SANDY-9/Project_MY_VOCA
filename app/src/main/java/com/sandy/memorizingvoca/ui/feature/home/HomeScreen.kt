package com.sandy.memorizingvoca.ui.feature.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun HomeRoute(
    onAppFinish: () -> Unit,
) {
    BackHandler(enabled = true) {
        onAppFinish()
    }
    HomeScreen()
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
) {

}

@Preview
@Composable
private fun HomeScreenPreview() {
    MemorizingVocaTheme {
        HomeScreen()
    }
}