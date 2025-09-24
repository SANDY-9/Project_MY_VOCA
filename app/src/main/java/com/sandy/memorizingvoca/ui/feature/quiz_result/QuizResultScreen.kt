package com.sandy.memorizingvoca.ui.feature.quiz_result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun QuizResultRoute(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    QuizResultScreen()
}

@Composable
private fun QuizResultScreen(
    modifier: Modifier = Modifier,
) {
    Box {
        Text(text = "되나욤?")
    }
}

@Preview
@Composable
private fun QuizResultScreenPreview() {
    MemorizingVocaTheme {
        QuizResultScreen(
            modifier = Modifier,
        )
    }
}
