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
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTopBar
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaTitleView
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: VocaDetailsViewModel = hiltViewModel(),
) {
    val voca by viewModel.voca.collectAsStateWithLifecycle()
    VocaDetailsScreen(
        voca = voca,
    )
}
@Composable
private fun VocaDetailsScreen(
    voca: Vocabulary? = null,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier.fillMaxSize(),
    ) {
        stickyHeader {
            VocaDetailsTopBar(
                day = voca?.day,
                onNavigateBack = {},
                onHighLightChange = {},
                onBookmarkChange = {},
            )
            VocaTitleView(
                vocabulary = voca,
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