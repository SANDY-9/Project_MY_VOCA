package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.room.entities.Vocabulary
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarkList(day: Int): Flow<Map<Int, List<Vocabulary>>>
    suspend fun addBookmark(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        highlighted: Boolean,
    )
    suspend fun deleteBookmark(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        highlighted: Boolean,
    )
}