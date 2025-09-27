package com.sandy.memorizingvoca.ui.feature.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.common.VocaWithBookmarkCard
import com.sandy.memorizingvoca.ui.extensions.addFocusCleaner
import com.sandy.memorizingvoca.ui.feature.bookmark.components.BookmarkDayStickyHeader
import com.sandy.memorizingvoca.ui.feature.bookmark.components.BookmarkListHeader
import com.sandy.memorizingvoca.ui.feature.bookmark.components.BookmarkSearchBar
import com.sandy.memorizingvoca.ui.feature.bookmark.components.BookmarkTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.rememberTTSManager

@Composable
internal fun BookmarkRoute(
    onNavigateFullScreen: () -> Unit,
    onNavigateQuiz1: () -> Unit,
    onNavigateQuiz2: () -> Unit,
    onNavigateDetails: (Int) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel(),
) {
    val bookmarkUiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()
    BookmarkScreen(
        bookmarkCount = bookmarkUiState.bookmarkCount,
        blindMode = bookmarkUiState.blindMode,
        query = bookmarkUiState.query,
        queryTitle = bookmarkUiState.currentQueryTitle,
        itemCount = bookmarkUiState.itemCount,
        bookmarkList = bookmarkUiState.filteredBookmarkMapByDay,
        onSearchVoca = viewModel::searchVoca,
        onBlindModeChange = viewModel::onBlindModeChange,
        onNavigateFullScreen = onNavigateFullScreen,
        onNavigateQuiz1 = onNavigateQuiz1,
        onNavigateQuiz2 = onNavigateQuiz2,
        onNavigateDetails = onNavigateDetails,
        onAllDeleteClick = viewModel::deleteMultipleBookmark,
        onBookmarkDelete = viewModel::deleteBookmark,
    )
}

@Composable
private fun BookmarkScreen(
    bookmarkCount: Int,
    blindMode: Boolean,
    query: String?,
    queryTitle: String,
    itemCount: Int,
    bookmarkList: Map<Int, List<Vocabulary>>,
    onSearchVoca: (String) -> Unit,
    onBlindModeChange: (Boolean) -> Unit,
    onAllDeleteClick: () -> Unit,
    onBookmarkDelete: (Vocabulary) -> Unit,
    onNavigateFullScreen: () -> Unit,
    onNavigateQuiz1: () -> Unit,
    onNavigateQuiz2: () -> Unit,
    onNavigateDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val ttsManager = rememberTTSManager()

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
        BookmarkListHeader(
            itemCount = itemCount,
            currentQueryText = queryTitle,
            onAllDeleteClick = onAllDeleteClick,
        )
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
        ) {
            bookmarkList.forEach { (day, vocaList) ->
                stickyHeader {
                    BookmarkDayStickyHeader(
                        day = day,
                        count = vocaList.size,
                    )
                }
                items(vocaList) { voca ->
                    VocaWithBookmarkCard(
                        word = voca.word,
                        meaning = voca.meaning,
                        highlighted = voca.highlighted,
                        bookmarked = voca.bookmarked,
                        blindMode = blindMode,
                        onSpeak = {
                            ttsManager.speak(voca.word)
                        },
                        onClick = {
                            onNavigateDetails(voca.vocaId)
                        },
                        onBookmarkChange = {
                            onBookmarkDelete(voca)
                        },
                    )
                }
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
            queryTitle = "전체",
            itemCount = 7,
            onSearchVoca = {},
            onBlindModeChange = {},
            onNavigateFullScreen = {},
            onNavigateQuiz1 = {},
            onNavigateQuiz2 = {},
            onAllDeleteClick = {},
            onBookmarkDelete = {},
            onNavigateDetails = {},
            bookmarkList = mapOf(
                1 to listOf(
                    Vocabulary(
                        vocaId = 1,
                        day = 1,
                        word = "advocate",
                        meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                        highlighted = false,
                        bookmarked = false,
                    ),
                    Vocabulary(
                        vocaId = 2,
                        day = 1,
                        word = "reclaim",
                        meaning = "[동] ① 되찾다 ② 개간하다 ③ 교화하다",
                        highlighted = false,
                        bookmarked = false,
                    ),
                ),
                5 to listOf(
                    Vocabulary(
                        vocaId = 1,
                        day = 5,
                        word = "bring[put] A to life",
                        meaning = "-을 소생시키다, -에 활기를 불어넣다",
                        highlighted = false,
                        bookmarked = false,
                    ),
                    Vocabulary(
                        vocaId = 2,
                        day = 5,
                        word = "have nothing to do with",
                        meaning = "-와 관계가 없다",
                        highlighted = false,
                        bookmarked = false,
                    ),
                ),
            )
        )
    }
}
