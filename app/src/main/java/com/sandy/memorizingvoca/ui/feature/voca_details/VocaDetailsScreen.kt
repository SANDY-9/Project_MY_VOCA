package com.sandy.memorizingvoca.ui.feature.voca_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTopBar
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaTitleView
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaDetailsRoute(
    onNavigateBack: () -> Unit,
) {
    VocaDetailsScreen()
}
@Composable
private fun VocaDetailsScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier.fillMaxSize(),
    ) {
        stickyHeader {
            VocaDetailsTopBar(
                day = 1,
                onNavigateBack = {},
                onBookmarkChange = {},
            )
            VocaTitleView(
                voca = Vocabulary(
                    vocaId = 4,
                    day = 1,
                    word = "dictate",
                    meaning = "[동] ① 명령하다, 지시하다 ② 받아쓰게 하다, 구술하다 [명] 명령, 지시[주로 pl.]",
                ),
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
        VocaDetailsScreen()
    }
}