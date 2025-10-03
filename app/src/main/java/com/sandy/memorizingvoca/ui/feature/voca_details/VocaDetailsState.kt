package com.sandy.memorizingvoca.ui.feature.voca_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.data.model.VocabularyDetails
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

internal interface VocaDetailsState {
    data object Loading: VocaDetailsState
    data class Success(val details: VocabularyDetails): VocaDetailsState
    data object Fail: VocaDetailsState
}

@Composable
internal fun VocaDetailsLoadingView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
internal fun VocaDetailsFailView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "네트워크 연결상태를 확인해주세요.",
            color = Color.Gray,
        )
    }
}

@Preview
@Composable
private fun VocaDetailsLoadingViewPreview() {
    MemorizingVocaTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            VocaDetailsLoadingView(
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
            HorizontalDivider()
            VocaDetailsFailView(
                modifier = Modifier.weight(1f).fillMaxWidth()
            )
        }
    }
}