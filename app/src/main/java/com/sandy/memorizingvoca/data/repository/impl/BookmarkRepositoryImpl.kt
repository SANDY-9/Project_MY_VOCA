package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: VocabularyDao,
) : BookmarkRepository {
    override fun getBookmarkList(): Flow<List<Vocabulary>> {
        return dao.getBookmarkList().flowOn(Dispatchers.IO)
    }

    override suspend fun addBookmark(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        highlighted: Boolean
    ) = withContext(Dispatchers.IO) {
        dao.updateBookmark(
            Vocabulary(
                vocaId = vocaId,
                day = day,
                word = word,
                meaning = meaning,
                highlighted = highlighted,
                bookmarked = true,
            )
        )
    }

    override suspend fun addMutipleBookmark(vocaList: List<Vocabulary>) = withContext(Dispatchers.IO) {
        dao.updateMultipleBookmark(vocaList)
    }

    override suspend fun deleteBookmark(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        highlighted: Boolean
    ) = withContext(Dispatchers.IO) {
        dao.updateBookmark(
            Vocabulary(
                vocaId = vocaId,
                day = day,
                word = word,
                meaning = meaning,
                highlighted = highlighted,
                bookmarked = false,
            )
        )
    }
}