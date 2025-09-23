package com.sandy.memorizingvoca.data.network

import com.sandy.memorizingvoca.data.model.VocabularyDetails

interface VocaDetailsDataSource {
    suspend fun getVocabularyDetails(word: String): VocabularyDetails
}