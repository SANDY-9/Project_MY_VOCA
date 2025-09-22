package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.VocaQuiz

interface GetQuizRepository {
    suspend fun addNewQuizResult(
        day: Int,
        wrongCount: Int,
        totalCount: Int,
        vararg wrongVocaId: Int,
    )

    suspend fun deleteQuiz(quiz: VocaQuiz)

    suspend fun getQuizList(day: Int): List<VocaQuiz>
    suspend fun getQuizResult(quizDate: String): VocaQuiz
}