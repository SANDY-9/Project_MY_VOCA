package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Vocabulary
import kotlinx.coroutines.flow.Flow

interface GetVocabularyRepository {
    suspend fun getVocaDayList(): List<Int>
    fun getVocaList(day: Int): Flow<List<Vocabulary>>
    fun getVocabularyDetails(vocaId: Int): Flow<Vocabulary>
}