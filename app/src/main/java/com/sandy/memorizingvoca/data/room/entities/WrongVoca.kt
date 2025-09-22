package com.sandy.memorizingvoca.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voca_wrong")
data class WrongVoca(
    val quizId: Int,
    val vocaId: Int,
    @PrimaryKey(autoGenerate = true)
    val wrongVocaId: Int = 0,
)
