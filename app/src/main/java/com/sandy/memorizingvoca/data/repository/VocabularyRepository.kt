package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.model.Vocabulary

interface VocabularyRepository {
    suspend fun addVocabularyList(vocaList: List<Vocabulary>)
}