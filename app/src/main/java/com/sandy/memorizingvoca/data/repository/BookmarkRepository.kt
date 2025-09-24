package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Vocabulary
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkList(): Flow<Map<Int, List<Vocabulary>>>
    suspend fun addBookmark(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        highlighted: Boolean,
    )
    suspend fun addMutipleBookmark(
        vocaList: List<Vocabulary>,
    )
    suspend fun deleteBookmark(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        highlighted: Boolean,
    )
}