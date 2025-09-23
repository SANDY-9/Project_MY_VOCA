package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.model.VocabularyDetails
import kotlinx.coroutines.flow.Flow

interface GetVocabularyRepository {
    suspend fun getVocaDayList(): List<Int>
    fun getVocaList(day: Int): Flow<List<Vocabulary>>
    fun getVocabulary(vocaId: Int): Flow<Vocabulary>
    suspend fun getVocabularyDetails(word: String): VocabularyDetails
}