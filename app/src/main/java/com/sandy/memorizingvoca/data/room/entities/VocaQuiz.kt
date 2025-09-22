package com.sandy.memorizingvoca.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voca_quiz")
data class VocaQuiz(
    val day: Int,
    val wrongCount: Int,
    val totalCount: Int = 50,
    val date: String,
    @PrimaryKey(autoGenerate = true)
    val quizId: Int = 0,
)
