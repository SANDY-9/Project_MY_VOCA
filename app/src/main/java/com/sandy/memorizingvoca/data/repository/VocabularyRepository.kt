package com.sandy.memorizingvoca.data.repository

import com.sandy.memorizingvoca.data.room.entities.Vocabulary

interface VocabularyRepository {
    suspend fun addVocabularyList(vocaList: List<Vocabulary>)
}