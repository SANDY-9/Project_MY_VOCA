package com.sandy.memorizingvoca.data.repository.impl

import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: VocabularyDao,
) : BookmarkRepository {

    override suspend fun addBookmark(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        pron: String?,
        highlighted: Boolean,
    ) = withContext(Dispatchers.IO) {
        dao.updateBookmark(
            Vocabulary(
                vocaId = vocaId,
                day = day,
                word = word,
                meaning = meaning,
                pron = pron,
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
        pron: String?,
        highlighted: Boolean
    ) = withContext(Dispatchers.IO) {
        dao.updateBookmark(
            Vocabulary(
                vocaId = vocaId,
                day = day,
                word = word,
                meaning = meaning,
                highlighted = highlighted,
                pron = pron,
                bookmarked = false,
            )
        )
    }

    override suspend fun deleteMutipleBookmark(vocaList: List<Vocabulary>) = withContext(Dispatchers.IO) {
        val updateList = vocaList.map { it.copy(bookmarked = false) }
        dao.updateMultipleBookmark(updateList)
    }
}