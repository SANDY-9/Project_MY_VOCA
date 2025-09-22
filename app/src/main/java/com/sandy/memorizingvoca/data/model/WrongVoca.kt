package com.sandy.memorizingvoca.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voca_wrong")
data class WrongVoca(
    val quizDate: String,
    val vocaId: Int,
    @PrimaryKey(autoGenerate = true)
    val wrongVocaId: Int = 0,
)
