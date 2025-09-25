package com.sandy.memorizingvoca.ui.feature.quiz2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.ui.feature.quiz2.components.Quiz2TopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun Quiz2Route(
    onNavigateBack: () -> Unit,
    onNavigateResult: (String) -> Unit,
    viewModel: Quiz2ViewModel = hiltViewModel(),
) {
    val quiz2State by viewModel.quiz2State.collectAsStateWithLifecycle()
    val gameSetState by viewModel.gameSetState.collectAsStateWithLifecycle()

    Quiz2Screen(
        title = quiz2State.title,
        remainsCount = quiz2State.remainsCount,
        totalCount = quiz2State.totalCount,
        onNavigateBack = onNavigateBack,
    )
}

@Composable
private fun Quiz2Screen(
    title: String,
    remainsCount: Int,
    totalCount: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Quiz2TopBar(
            title = title,
            totalCount = totalCount,
            remainsCount = remainsCount,
            onNavigateBack = onNavigateBack,
        )
    }
}

@Preview
@Composable
private fun Quiz2ScreenPreview() {
    MemorizingVocaTheme {
        Quiz2Screen(
            title = "Day 01",
            remainsCount = 19,
            totalCount = 50,
            onNavigateBack = {},
        )
    }
}
