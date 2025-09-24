package com.sandy.memorizingvoca.data.repository.impl

import androidx.room.Transaction
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.WrongVoca
import com.sandy.memorizingvoca.data.repository.QuizRepository
import com.sandy.memorizingvoca.data.room.dao.QuizDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao
) : QuizRepository {
    override suspend fun addNewQuizResult(
        date: String,
        day: Int,
        totalCount: Int,
        incorrectedVocaId: List<Int>,
    ) = withContext(Dispatchers.IO) {
        dao.addNewQuiz(
            VocaQuiz(
                date = date,
                day = day,
                wrongCount = incorrectedVocaId.size,
                totalCount = totalCount,
            )
        )
        dao.addNewWrongVoca(
            incorrectedVocaId.map { id ->
                WrongVoca(
                    quizDate = date,
                    vocaId = id,
                )
            }
        )
    }

    @Transaction
    override suspend fun deleteQuiz(quiz: VocaQuiz) = withContext(Dispatchers.IO) {
        dao.deleteQuiz(quiz)
        dao.deleteWrongVoca(quiz.date)
    }
}