package com.sandy.memorizingvoca.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sandy.memorizingvoca.data.room.entities.VocaQuiz
import com.sandy.memorizingvoca.data.room.entities.Vocabulary
import com.sandy.memorizingvoca.data.room.entities.WrongVoca

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
                "WHERE quizId = :quizId"
    )
    suspend fun deleteWrongVoca(quizId: Int)

    @Query(
        "SELECT * FROM voca_quiz " +
                "WHERE day = :day " +
                "ORDER BY date ASC"
    )
    suspend fun getQuizList(day: Int): List<VocaQuiz>

    @Transaction
    @Query(
        "SELECT v.* FROM voca_list v " +
                "INNER JOIN voca_wrong w " +
                "ON v.vocaId = w.vocaId " +
                "WHERE w.quizId = :quizId"
    )
    suspend fun getWrongVocaList(quizId: Int): List<Vocabulary>

}
