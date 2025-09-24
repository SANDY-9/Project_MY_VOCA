package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.room.dao.QuizDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetQuizRepositoryImpl @Inject constructor(
    private val dao: QuizDao,
) : GetQuizRepository {
    override suspend fun getQuizList(day: Int): List<VocaQuiz> = withContext(Dispatchers.IO) {
        return@withContext dao.getQuizList(day)
    }

    override fun getQuizResult(quizDate: String): Flow<VocaQuiz> {
        return dao.getQuiz(quizDate)
    }

    override fun getWrongVocaList(quizDate: String): Flow<List<Vocabulary>> {
        return dao.getWrongVocaList(quizDate).flowOn(Dispatchers.IO)
    }
}