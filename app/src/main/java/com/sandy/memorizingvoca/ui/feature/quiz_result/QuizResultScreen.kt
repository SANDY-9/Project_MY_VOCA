package com.sandy.memorizingvoca.ui.feature.quiz_result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun QuizResultRoute(
    onNavigateBack: () -> Unit,
    viewModel: QuizResultViewModel = hiltViewModel(),
) {

    val quizResult by viewModel.quizResult.collectAsStateWithLifecycle()
    val incorrectedList by viewModel.incorrectedVocaList.collectAsStateWithLifecycle()

    QuizResultScreen(
        title = quizResult?.title,
        date = quizResult?.date,
        correctCount = quizResult?.correctCount,
        totalCount = quizResult?.totalCount,
        incorrectedList = incorrectedList,
        onNavigateBack = onNavigateBack,
    )
}

@Composable
private fun QuizResultScreen(
    title: String?,
    date: String?,
    correctCount: Int?,
    totalCount: Int?,
    incorrectedList: List<Vocabulary>,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Text(text = title ?: "")
        Text(text = date ?: "")
        Text(text = "$correctCount")
        Text(text = "$totalCount")
        incorrectedList.forEach {
            Text(it.word)
        }
    }
}

@Preview
@Composable
private fun QuizResultScreenPreview() {
    MemorizingVocaTheme {
        QuizResultScreen(
            title = "Day 03",
            date = "2025년 09월 24일 18:45",
            correctCount = 41,
            totalCount = 50,
            incorrectedList = emptyList(),
            onNavigateBack = {},
        )
    }
}
