package com.sandy.memorizingvoca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voca_quiz")
data class VocaQuiz(
    @PrimaryKey
    val date: String,
    val day: Int,
    val wrongCount: Int,
    val totalCount: Int,
)
