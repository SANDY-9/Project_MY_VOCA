package com.sandy.memorizingvoca.ui.feature.quiz1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.ui.common.QuizProgressIndicator
import com.sandy.memorizingvoca.ui.feature.quiz1.components.Quiz1QuizView
import com.sandy.memorizingvoca.ui.feature.quiz1.components.Quiz1TopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun Quiz1Route(
    onNavigateBack: () -> Unit,
    onNavigateResult: (String) -> Unit,
    viewModel: Quiz1ViewModel = hiltViewModel(),
) {
    val quiz1State by viewModel.quiz1State.collectAsStateWithLifecycle()
    val questionState by viewModel.questionState.collectAsStateWithLifecycle()

    LaunchedEffect(quiz1State.quiz1Status) {
        if(quiz1State.quiz1Status == Quiz1Status.DONE) {
            onNavigateResult(quiz1State.quizDate ?: return@LaunchedEffect)
        }
    }

    Quiz1Screen(
        title = quiz1State.title,
        correctCount = quiz1State.correctCount,
        totalCount = quiz1State.totalCount,
        questionNumTitle = questionState.questionNumTitle,
        question = questionState.question,
        options = questionState.options,
        answerIndex = questionState.answerIndex,
        quiz1Status = quiz1State.quiz1Status,
        onNavigateBack = onNavigateBack,
        onOptionSelect = viewModel::checkAnswer,
    )
}

@Composable
private fun Quiz1Screen(
    title: String,
    correctCount: Int,
    totalCount: Int,
    questionNumTitle: String,
    question: String,
    options: List<String>,
    answerIndex: Int?,
    quiz1Status: Quiz1Status,
    onNavigateBack: () -> Unit,
    onOptionSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Quiz1TopBar(
            title = title,
            correctCount = correctCount,
            totalCount = totalCount,
            onNavigateBack = onNavigateBack,
        )
        QuizProgressIndicator(
            progressed = quiz1Status == Quiz1Status.SOLVING_QUESTIONS,
            durationMillis = QUIZ1_TIME_OUT,
        )
        Quiz1QuizView(
            questionNumTitle = questionNumTitle,
            questionWord = question,
            quiz1Status = quiz1Status,
            options = options,
            answerIndex = answerIndex ?: -1,
            onOptionSelect = onOptionSelect,
        )
    }
}

@Composable
@Preview
private fun Quiz1ScreenPreview() {
    var quiz1Status by remember { mutableStateOf(Quiz1Status.SOLVING_QUESTIONS) }
    MemorizingVocaTheme {
       Quiz1Screen(
           title = "Day 03",
           correctCount = 4,
           totalCount = 50,
           questionNumTitle = "01.",
           question = "respect",
           options = listOf(
               "[형] 눈에 잘 띄는, 뚜렷한",
               "[동] 존경하다 [명] ① 존경 ② (측)면",
               "[동] 명시하다, 구체화하다",
               "[명] ① 관점, 시각 ② 원근법",
           ),
           answerIndex = 1,
           quiz1Status = quiz1Status,
           onNavigateBack = {},
           onOptionSelect = { selectedIndex ->
               quiz1Status = if(selectedIndex == 1) Quiz1Status.CORRECT else Quiz1Status.INCORRECT
           },
       )
    }
}