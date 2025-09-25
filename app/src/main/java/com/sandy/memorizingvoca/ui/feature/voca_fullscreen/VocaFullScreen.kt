package com.sandy.memorizingvoca.ui.feature.voca_fullscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components.FullScreenFooter
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components.FullScreenTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaFullScreenRoute(
    onNavigateBack: () -> Unit,
    viewModel: VocaFullScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.fullScreenState.collectAsStateWithLifecycle()
    VocaFullScreen(
        title = state.title,
        blindMode = state.blindMode,
        autoMode = state.autoMode,
        page = state.currentPage,
        totalPage = state.totalPage,
        onBlindModeChange = viewModel::onBlindModeChange,
        onAutoModeChange = viewModel::onAutoModeChange,
        onNavigateBack = onNavigateBack,
    )
}

@Composable
private fun VocaFullScreen(
    title: String,
    blindMode: Boolean,
    autoMode: Boolean,
    page: Int,
    totalPage: Int,
    onBlindModeChange: (Boolean) -> Unit,
    onAutoModeChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        FullScreenTopBar(
            title = title,
            blindMode = blindMode,
            autoMode = autoMode,
            onBlindModeChange = onBlindModeChange,
            onAutoModeChange = onAutoModeChange,
            onNavigateBack = onNavigateBack,
        )
        Spacer(modifier = modifier.weight(1f))
        FullScreenFooter(
            page = page,
            totalPage = totalPage,
        )

    }

}

@Preview
@Composable
private fun VocaFullScreenPreview() {
    MemorizingVocaTheme {
        VocaFullScreen(
            title = "Day 01",
            blindMode = false,
            autoMode = false,
            page = 2,
            totalPage = 50,
            onBlindModeChange = {},
            onAutoModeChange = {},
            onNavigateBack = {},
        )
    }
}
