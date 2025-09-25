package com.sandy.memorizingvoca.ui.feature.quiz1

import com.sandy.memorizingvoca.data.model.Vocabulary

internal data class Quiz1State(
    val title: String = "",
    val vocaList: List<Vocabulary> = emptyList(),
    val correctCount: Int = 0,
    val totalCount: Int = vocaList.size,
    val quiz1Status: Quiz1Status = Quiz1Status.NONE,
    val incorrectedList: List<Vocabulary> = emptyList(),
    val quizDate: String? = null,
)

internal data class Quiz1QuestionState(
    val index: Int? = null,
    val question: String = "",
    val questionNumTitle: String = "",
    val options: List<String> = emptyList(),
    val answerIndex: Int? = null,
    val answerVoca: Vocabulary? = null,
)

internal enum class Quiz1Status {
    NONE,
    SOLVING_QUESTIONS,
    CORRECT,
    INCORRECT,
    DONE,
}

const val QUIZ1_TIME_OUT = 5000