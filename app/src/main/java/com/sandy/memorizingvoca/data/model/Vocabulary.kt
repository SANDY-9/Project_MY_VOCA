package com.sandy.memorizingvoca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voca_list")
data class Vocabulary(
    @PrimaryKey
    val vocaId: Int,
    val day: Int,
    val word: String,
    val meaning: String,
    val pron: String? = null,
    val highlighted: Boolean = false,
    val bookmarked: Boolean = false,
)
