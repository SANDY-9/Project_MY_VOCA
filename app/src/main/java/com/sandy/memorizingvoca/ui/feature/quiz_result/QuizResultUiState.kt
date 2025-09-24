package com.sandy.memorizingvoca.ui.feature.quiz_result

internal data class QuizResultUiState(
    val title: String,
    val date: String,
    val correctCount: Int,
    val incorrectCount: Int,
    val totalCount: Int,
    val percentage: Int,
    val deleted: Boolean,
)
