package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetVocabularyRepositoryImpl @Inject constructor(
    private val dao: VocabularyDao,
) : GetVocabularyRepository {
    override suspend fun getVocaDayList(): List<Int> = withContext(Dispatchers.IO) {
        return@withContext dao.getAllDays()
    }

    override fun getVocaList(day: Int): Flow<List<Vocabulary>> {
        return dao.getVocaList(day).flowOn(Dispatchers.IO)
    }

    override suspend fun getVocabulary(vocaId: Int): Vocabulary = withContext(Dispatchers.IO) {
        return@withContext dao.getVocabulary(vocaId)
    }
}