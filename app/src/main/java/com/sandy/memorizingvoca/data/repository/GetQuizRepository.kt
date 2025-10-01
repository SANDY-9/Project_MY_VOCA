package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.room.dao.DayCount
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface GetQuizRepository {
    fun getQuizListForDate(date: LocalDate): Flow<List<VocaQuiz>>
    fun getQuizListForCalendar(): Flow<Map<Date, List<VocaQuiz>>>
    suspend fun getQuizResult(quizDate: String): VocaQuiz
    fun getWrongVocaList(quizDate: String): Flow<List<Vocabulary>>
    fun getAllDaysWithCount(): Flow<List<DayCount>>
}