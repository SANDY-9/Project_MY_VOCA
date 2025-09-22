package com.sandy.memorizingvoca.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sandy.memorizingvoca.data.room.dao.QuizDao
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import com.sandy.memorizingvoca.data.room.entities.VocaQuiz
import com.sandy.memorizingvoca.data.room.entities.Vocabulary
import com.sandy.memorizingvoca.data.room.entities.WrongVoca

@Database(entities = [Vocabulary::class, VocaQuiz::class, WrongVoca::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun quizDao(): QuizDao
}