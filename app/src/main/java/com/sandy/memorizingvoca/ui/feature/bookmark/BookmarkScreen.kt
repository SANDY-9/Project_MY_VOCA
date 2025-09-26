package com.sandy.memorizingvoca.ui.feature.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.ui.feature.bookmark.components.BookmarkTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun BookmarkRoute(
    onNavigateFullScreen: () -> Unit,
    onNavigateQuiz1: () -> Unit,
    onNavigateQuiz2: () -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val bookmarkUiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()
    BookmarkScreen(
        bookmarkCount = bookmarkUiState.bookmarkCount,
        blindMode = bookmarkUiState.blindMode,
        onBlindModeChange = viewModel::onBlindModeChange,
        onNavigateFullScreen = onNavigateFullScreen,
        onNavigateQuiz1 = onNavigateQuiz1,
        onNavigateQuiz2 = onNavigateQuiz2,
    )
}

@Composable
private fun BookmarkScreen(
    bookmarkCount: Int,
    blindMode: Boolean,
    onBlindModeChange: (Boolean) -> Unit,
    onNavigateFullScreen: () -> Unit,
    onNavigateQuiz1: () -> Unit,
    onNavigateQuiz2: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        BookmarkTopBar(
            bookmarkCount = bookmarkCount,
            blindMode = blindMode,
            fullScreenEnabled = bookmarkCount > 0,
            quiz1Enabled = bookmarkCount >= 4,
            quiz2Enabled = bookmarkCount >= 6,
            onBlindModeChange = onBlindModeChange,
            onNavigateFullScreen = onNavigateFullScreen,
            onNavigateQuiz1 = onNavigateQuiz1,
            onNavigateQuiz2 = onNavigateQuiz2,
        )
    }
}

@Preview
@Composable
private fun BookmarkScreenPreview() {
    MemorizingVocaTheme {
        BookmarkScreen(
            bookmarkCount = 10,
            blindMode = false,
            onBlindModeChange = {},
            onNavigateFullScreen = {},
            onNavigateQuiz1 = {},
            onNavigateQuiz2 = {},
        )
    }
}
