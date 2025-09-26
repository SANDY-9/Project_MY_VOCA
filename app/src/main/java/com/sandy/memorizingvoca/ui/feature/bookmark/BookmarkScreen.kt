package com.sandy.memorizingvoca.ui.feature.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.ui.extensions.addFocusCleaner
import com.sandy.memorizingvoca.ui.feature.bookmark.components.BookmarkListHeader
import com.sandy.memorizingvoca.ui.feature.bookmark.components.BookmarkSearchBar
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
        query = bookmarkUiState.query,
        itemCount = bookmarkUiState.itemCount,
        onSearchVoca = viewModel::searchVoca,
        onBlindModeChange = viewModel::onBlindModeChange,
        onNavigateFullScreen = onNavigateFullScreen,
        onNavigateQuiz1 = onNavigateQuiz1,
        onNavigateQuiz2 = onNavigateQuiz2,
        onResetClick = viewModel::resetBookmarkList,
        onAllDeleteClick = viewModel::deleteMultipleBookmark,
    )
}

@Composable
private fun BookmarkScreen(
    bookmarkCount: Int,
    blindMode: Boolean,
    query: String?,
    itemCount: Int,
    onSearchVoca: (String) -> Unit,
    onBlindModeChange: (Boolean) -> Unit,
    onResetClick: () -> Unit,
    onAllDeleteClick: () -> Unit,
    onNavigateFullScreen: () -> Unit,
    onNavigateQuiz1: () -> Unit,
    onNavigateQuiz2: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
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
        BookmarkSearchBar(
            query = query,
            focusManager = focusManager,
            focusRequester = focusRequester,
            onSearchVoca = onSearchVoca,
        )
        LazyColumn(
            modifier = modifier.fillMaxWidth()
        ) {
            stickyHeader {
                BookmarkListHeader(
                    itemCount = itemCount,
                    onAllDeleteClick = onAllDeleteClick,
                )
            }
        }
    }
}

@Preview
@Composable
private fun BookmarkScreenPreview() {
    MemorizingVocaTheme {
        BookmarkScreen(
            bookmarkCount = 10,
            blindMode = false,
            query = "Day 1",
            itemCount = 7,
            onSearchVoca = {},
            onBlindModeChange = {},
            onNavigateFullScreen = {},
            onNavigateQuiz1 = {},
            onNavigateQuiz2 = {},
            onResetClick = {},
            onAllDeleteClick = {},
        )
    }
}
