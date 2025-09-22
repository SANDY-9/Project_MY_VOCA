package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.room.entities.Vocabulary
import kotlinx.coroutines.flow.Flow

interface GetVocabularyRepository {
    suspend fun getVocaDayList(): List<Int>
    fun getVocaList(day: Int): Flow<List<Vocabulary>>
    suspend fun getVocabulary(vocaId: Int): Vocabulary
}