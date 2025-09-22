package com.sandy.memorizingvoca.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.model.WrongVoca
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewQuiz(quiz: VocaQuiz)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewWrongVoca(wrongVoca: WrongVoca)

    @Delete
    suspend fun deleteQuiz(quiz: VocaQuiz)
    @Query(
        "DELETE FROM voca_wrong " +
                "WHERE quizDate = :quizDate"
    )
    suspend fun deleteWrongVoca(quizDate: String)

    @Query(
        "SELECT * FROM voca_quiz " +
                "WHERE day = :day " +
                "ORDER BY date ASC"
    )
    suspend fun getQuizList(day: Int): List<VocaQuiz>

    @Query(
        "SELECT * FROM voca_quiz " +
                "WHERE date = :quizDate "
    )
    suspend fun getQuiz(quizDate: String): VocaQuiz

    @Transaction
    @Query(
        "SELECT v.* FROM voca_list v " +
                "INNER JOIN voca_wrong w " +
                "ON v.vocaId = w.vocaId " +
                "WHERE w.quizDate = :quizDate"
    )
    fun getWrongVocaList(quizDate: String): Flow<List<Vocabulary>>

}
