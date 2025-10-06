package com.sandy.memorizingvoca.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sandy.memorizingvoca.data.room.dao.QuizDao
import com.sandy.memorizingvoca.data.room.dao.VocabularyDao
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.model.WrongVoca

@Database(
    entities = [Vocabulary::class, VocaQuiz::class, WrongVoca::class],
    version = 1,
    exportSchema = true,
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun quizDao(): QuizDao
}