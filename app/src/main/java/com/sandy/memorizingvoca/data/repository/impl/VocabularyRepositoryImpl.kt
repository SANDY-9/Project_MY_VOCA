package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.VocabularyRepository
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class VocabularyRepositoryImpl @Inject constructor(
    private val dao: VocabularyDao,
): VocabularyRepository {
    override suspend fun addVocabularyList(vocaList: List<Vocabulary>) = withContext(Dispatchers.IO) {
        val isNotExistData = dao.getAllDays().isEmpty()
        if(isNotExistData) {
            dao.addVocabularyList(vocaList)
        }
    }
}