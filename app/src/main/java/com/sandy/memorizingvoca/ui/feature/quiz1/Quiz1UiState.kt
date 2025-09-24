package com.sandy.memorizingvoca.ui.feature.quiz1

import com.sandy.memorizingvoca.data.model.Vocabulary

internal data class Quiz1UiState(
    val title: String = "",
    val vocaList: List<Vocabulary> = emptyList(),
    val correctCount: Int = 0,
    val totalCount: Int = vocaList.size,
    val answerState: AnswerState = AnswerState.NONE,
)

internal data class Quiz1QuestionState(
    val index: Int? = null,
    val question: String = "",
    val questionNumTitle: String = "",
    val options: List<String> = emptyList(),
    val answerIndex: Int? = null,
)

internal enum class AnswerState {
    NONE,
    SOLVING_QUESTIONS,
    CORRECT,
    INCORRECT,
}

internal const val QUIZ1_TIME_OUT = 5000