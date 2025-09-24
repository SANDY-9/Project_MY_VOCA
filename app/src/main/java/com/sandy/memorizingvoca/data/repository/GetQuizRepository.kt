package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import kotlinx.coroutines.flow.Flow

interface GetQuizRepository {
    suspend fun getQuizList(day: Int): List<VocaQuiz>
    suspend fun getQuizResult(quizDate: String): VocaQuiz
    fun getWrongVocaList(quizDate: String): Flow<List<Vocabulary>>
}