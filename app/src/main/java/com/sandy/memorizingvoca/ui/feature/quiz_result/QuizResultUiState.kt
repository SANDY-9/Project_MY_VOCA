package com.sandy.memorizingvoca.ui.feature.quiz_result

import com.sandy.memorizingvoca.data.model.VocaQuiz

internal data class QuizResultUiState(
    val quiz: VocaQuiz,
    val title: String,
    val date: String,
    val correctCount: Int,
    val incorrectCount: Int,
    val totalCount: Int,
    val percentage: Int,
    val deleted: Boolean,
    val blindMode: Boolean = false,
)
