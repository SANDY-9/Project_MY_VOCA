package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GetQuizRepository {
    fun getQuizListForDate(date: LocalDate): Flow<List<VocaQuiz>>
    fun getQuizListForCalendar(startDay: LocalDate, endDay: LocalDate): Flow<Map<Date, List<VocaQuiz>>>
    suspend fun getQuizResult(quizDate: String): VocaQuiz
    fun getWrongVocaList(quizDate: String): Flow<List<Vocabulary>>
    fun getExistDays(): Flow<List<Int>>
}