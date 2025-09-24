package com.sandy.memorizingvoca.ui.feature.quiz_result

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.feature.quiz_result.components.QuizResultListHeaderView
import com.sandy.memorizingvoca.ui.feature.quiz_result.components.QuizResultScoreView
import com.sandy.memorizingvoca.ui.feature.quiz_result.components.QuizResultTopBar
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
        correctCount = quizResult?.correctCount,
        incorrectCount = quizResult?.incorrectCount,
        totalCount = quizResult?.totalCount,
        percentage = quizResult?.percentage,
        date = quizResult?.date,
        incorrectedList = incorrectedList,
        onNavigateBack = onNavigateBack,
        onDeleteClick = {},
        onAllBookmarkClick = {},
    )
}

@Composable
private fun QuizResultScreen(
    title: String?,
    correctCount: Int?,
    incorrectCount: Int?,
    totalCount: Int?,
    percentage: Int?,
    date: String?,
    incorrectedList: List<Vocabulary>,
    onNavigateBack: () -> Unit,
    onDeleteClick: () -> Unit,
    onAllBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        stickyHeader {
            QuizResultTopBar(
                title = title,
                onNavigateBack = onNavigateBack,
                onDeleteClick = onDeleteClick,
            )
            QuizResultScoreView(
                date = date,
                correctCount = correctCount,
                totalCount = totalCount,
                percentage = percentage,
            )
            QuizResultListHeaderView(
                incorrectCount = incorrectCount,
                onAllBookmarkClick = onAllBookmarkClick,
            )
        }
    }
}

@Preview
@Composable
private fun QuizResultScreenPreview() {
    MemorizingVocaTheme {
        QuizResultScreen(
            title = "Day 03",
            correctCount = 41,
            incorrectCount = 9,
            totalCount = 50,
            percentage = 82,
            date = "2025년 09월 24일 18:45",
            incorrectedList = emptyList(),
            onNavigateBack = {},
            onDeleteClick = {},
            onAllBookmarkClick = {},
        )
    }
}
