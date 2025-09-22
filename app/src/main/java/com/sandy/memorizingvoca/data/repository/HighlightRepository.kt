package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Vocabulary
import kotlinx.coroutines.flow.Flow

interface HighlightRepository {
    fun getHighlightList(day: Int): Flow<Map<Int, List<Vocabulary>>>
    suspend fun addHighlight(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        bookmarked: Boolean,
    )
    suspend fun deleteHighlight(
        vocaId: Int,
        day: Int,
        word: String,
        meaning: String,
        bookmarked: Boolean,
    )
}