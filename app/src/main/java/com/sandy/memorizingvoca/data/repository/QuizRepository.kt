package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.VocaQuiz

interface QuizRepository {
    suspend fun addNewQuizResult(
        day: Int,
        wrongCount: Int,
        totalCount: Int,
        vararg wrongVocaId: Int,
    )
    suspend fun deleteQuiz(quiz: VocaQuiz)

}