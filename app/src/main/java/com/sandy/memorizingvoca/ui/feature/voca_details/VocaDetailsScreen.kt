package com.sandy.memorizingvoca.ui.feature.voca_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTitleView
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: VocaDetailsViewModel = hiltViewModel(),
) {
    val voca by viewModel.voca.collectAsStateWithLifecycle()
    val vocaDetails by viewModel.vocaDetails.collectAsStateWithLifecycle()
    VocaDetailsScreen(
        voca = voca,
        onNavigateBack = onNavigateBack,
        onHighlightChange = viewModel::updateHighlight,
        onBookmarkChange = viewModel::updateBookmark,
    )
}
@Composable
private fun VocaDetailsScreen(
    voca: Vocabulary?,
    onNavigateBack: () -> Unit,
    onHighlightChange: (Boolean) -> Unit,
    onBookmarkChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier.fillMaxSize(),
    ) {
        stickyHeader {
            VocaDetailsTopBar(
                voca = voca,
                onNavigateBack = onNavigateBack,
                onHighlightChange = onHighlightChange,
                onBookmarkChange = onBookmarkChange,
            )
            VocaDetailsTitleView(
                word = voca?.word ?: "",
                meaning = voca?.meaning ?: "",
                highlighted = voca?.highlighted ?: false,
            )
        }
        item {

        }
    }

}

@Composable
@Preview
private fun VocaDetailsScreenPreview() {
    MemorizingVocaTheme {
        VocaDetailsScreen(
            voca = Vocabulary(
                vocaId = 4,
                day = 1,
                word = "dictate",
                meaning = "[동] ① 명령하다, 지시하다 ② 받아쓰게 하다, 구술하다 [명] 명령, 지시[주로 pl.]",
            ),
            onNavigateBack = {},
            onHighlightChange = {},
            onBookmarkChange = {},
        )
    }
}