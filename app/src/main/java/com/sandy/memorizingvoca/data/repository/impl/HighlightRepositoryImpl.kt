package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.HighlightRepository
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HighlightRepositoryImpl @Inject constructor(
    private val dao: VocabularyDao,
) : HighlightRepository {
    override fun getHighlightList(day: Int): Flow<Map<Int, List<Vocabulary>>> {
        return dao.getHighlightList()
            .flowOn(Dispatchers.IO)
            .map { it.groupBy { voca -> voca.day } }
    }

    override suspend fun addHighlight(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        pron: String?,
        bookmarked: Boolean,
    ) = withContext(Dispatchers.IO) {
        dao.updateHighlight(
            Vocabulary(
                vocaId = vocaId,
                day = day,
                word = word,
                meaning = meaning,
                bookmarked = bookmarked,
                pron = pron,
                highlighted = true,
            )
        )
    }

    override suspend fun deleteHighlight(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        pron: String?,
        bookmarked: Boolean,
    ) = withContext(Dispatchers.IO) {
        dao.updateHighlight(
            Vocabulary(
                vocaId = vocaId,
                day = day,
                word = word,
                meaning = meaning,
                pron = pron,
                bookmarked = bookmarked,
                highlighted = false,
            )
        )
    }
}