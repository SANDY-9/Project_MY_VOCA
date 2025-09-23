package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.ExampleSentence
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.model.VocabularyDetails
import com.sandy.memorizingvoca.data.model.Word
import com.sandy.memorizingvoca.data.network.VocaDetailsDataSource
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class GetVocabularyRepositoryImpl @Inject constructor(
    private val dao: VocabularyDao,
    private val vocaDetailsDataSource: VocaDetailsDataSource,
) : GetVocabularyRepository {
    override suspend fun getVocaDayList(): List<Int> = withContext(Dispatchers.IO) {
        return@withContext dao.getAllDays()
    }

    override fun getVocaList(day: Int): Flow<List<Vocabulary>> {
        return dao.getVocaList(day).flowOn(Dispatchers.IO)
    }

    override fun getVocabulary(vocaId: Int): Flow<Vocabulary> {
        return dao.getVocabulary(vocaId)
    }

    override suspend fun getVocabularyDetails(word: String): VocabularyDetails = withContext(Dispatchers.IO) {
        return@withContext vocaDetailsDataSource.getVocabularyDetails(word)
    }
}
