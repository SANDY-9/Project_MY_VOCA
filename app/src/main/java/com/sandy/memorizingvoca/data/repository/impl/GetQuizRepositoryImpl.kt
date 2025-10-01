package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.room.dao.DayCount
import com.sandy.memorizingvoca.data.room.dao.QuizDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class GetQuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao,
) : GetQuizRepository {

    override fun getQuizListForDate(date: LocalDate): Flow<List<VocaQuiz>> {
        return dao.getQuizListForDate(date.toString()).flowOn(Dispatchers.IO)
    }

    override fun getQuizListForCalendar(): Flow<Map<Date, List<VocaQuiz>>> {
        return dao.getQuizListForCalendar().map { quizList ->
            quizList.associate { quiz ->
                val localDateTime = LocalDateTime.parse(quiz.date)
                val localDate = localDateTime.toLocalDate()
                val date = Date(localDate = localDate,)
                date to quizList.filter {
                    it.date.contains(localDate.toString())
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getQuizResult(quizDate: String): VocaQuiz = withContext(Dispatchers.IO) {
        return@withContext dao.getQuiz(quizDate)
    }

    override fun getWrongVocaList(quizDate: String): Flow<List<Vocabulary>> {
        return dao.getWrongVocaList(quizDate).flowOn(Dispatchers.IO)
    }

    override fun getAllDaysWithCount(): Flow<List<DayCount>> {
        return dao.getAllDaysWithCount()
    }
}