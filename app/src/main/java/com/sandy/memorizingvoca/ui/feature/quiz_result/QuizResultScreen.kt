package com.sandy.memorizingvoca.ui.feature.quiz_result

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.common.IncorrectVocaListCard
import com.sandy.memorizingvoca.ui.feature.quiz_result.components.QuizResultListHeaderView
import com.sandy.memorizingvoca.ui.feature.quiz_result.components.QuizResultScoreView
import com.sandy.memorizingvoca.ui.feature.quiz_result.components.QuizResultTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.rememberTTSManager

@Composable
internal fun QuizResultRoute(
    onNavigateBack: () -> Unit,
    viewModel: QuizResultViewModel = hiltViewModel(),
) {
    val quizResult by viewModel.quizResultUiState.collectAsStateWithLifecycle()
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
        onDeleteClick = viewModel::deleteQuizResult,
        onAllBookmarkClick = viewModel::addMultipleBookmark,
        onBookmarkChange = viewModel::updateBookmark,
        onItemClick = {},
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
    onBookmarkChange: (Vocabulary, Boolean) -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val ttsManager = rememberTTSManager()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
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
        items(incorrectedList) { voca ->
            IncorrectVocaListCard(
                word = voca.word,
                meaning = voca.meaning,
                highlighted = voca.highlighted,
                bookmarked = voca.bookmarked,
                onSpeak = {
                    ttsManager.speak(voca.word)
                },
                onClick = {
                    onItemClick(voca.vocaId)
                },
                onBookmarkChange = { bookmarked ->
                    onBookmarkChange(voca, bookmarked)
                },
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
            incorrectedList = listOf(
                Vocabulary(
                    vocaId = 1,
                    day = 1,
                    word = "ascribe",
                    meaning = "[동] -의 탓으로 돌리다"
                ),
                Vocabulary(
                    vocaId = 2,
                    day = 1,
                    word = "advocate",
                    meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    highlighted = true,
                ),
                Vocabulary(
                    vocaId = 3,
                    day = 1,
                    word = "affluent",
                    meaning = "[형] ① 풍부한 ② 부유한"
                ),
                Vocabulary(
                    vocaId = 4,
                    day = 1,
                    word = "dictate",
                    meaning = "[동] ① 명령하다, 지시하다 ② 받아쓰게 하다, 구술하다 [명] 명령, 지시[주로 pl.]",
                ),
                Vocabulary(
                    vocaId = 5,
                    day = 1,
                    word = "have trouble[difficulty] (in)",
                    meaning = "ing 하는 데 어려움[곤란]을 겪다"
                )
            ),
            onNavigateBack = {},
            onDeleteClick = {},
            onAllBookmarkClick = {},
            onBookmarkChange = { _, _ -> },
            onItemClick = {},
        )
    }
}
