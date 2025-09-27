package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.room.dao.QuizDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

class GetQuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao,
) : GetQuizRepository {

    override fun getQuizListForDate(date: LocalDate): Flow<List<VocaQuiz>> {
        return dao.getQuizListForDate(date.toString())
    }

    override fun getQuizListForCalendar(
        startDay: LocalDate,
        endDay: LocalDate
    ): Flow<List<VocaQuiz>> {
        return dao.getQuizListForCalendar(
            startDay = startDay.toString(),
            endDay = endDay.toString(),
        )
    }

    override suspend fun getQuizResult(quizDate: String): VocaQuiz = withContext(Dispatchers.IO) {
        return@withContext dao.getQuiz(quizDate)
    }

    override fun getWrongVocaList(quizDate: String): Flow<List<Vocabulary>> {
        return dao.getWrongVocaList(quizDate).flowOn(Dispatchers.IO)
    }

    override fun getExistDays(): Flow<List<Int>> {
        return dao.getExistDays()
    }
}