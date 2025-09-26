package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Vocabulary

interface BookmarkRepository {
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
    suspend fun deleteMutipleBookmark(
        vocaList: List<Vocabulary>,
    )
}