package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.VocaQuiz

interface QuizRepository {
    suspend fun addNewQuizResult(
        date: String,
        day: Int,
        totalCount: Int,
        incorrectedVocaId: List<Int>,
    )
    suspend fun deleteQuiz(quiz: VocaQuiz)

}