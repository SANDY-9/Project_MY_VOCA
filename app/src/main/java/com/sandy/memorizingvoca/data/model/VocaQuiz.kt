package com.sandy.memorizingvoca.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "voca_quiz")
data class VocaQuiz(
    @PrimaryKey
    val date: String,
    val day: Int,
    val wrongCount: Int,
    val totalCount: Int,
) {
    @Ignore
    val correctPercentage = (totalCount - wrongCount) * 100 / totalCount
    @Ignore
    val quizName = if(day == 0) "북마크" else "Day "+String.format("%02d", day)
}
