package com.sandy.memorizingvoca.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sandy.memorizingvoca.data.room.entities.Vocabulary
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabularyDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addVocabularyList(vocaList: List<Vocabulary>)

    @Update
    suspend fun updateHighlight(vocabulary: Vocabulary)
    @Update
    suspend fun updateBookmark(vocabulary: Vocabulary)

    @Query(
        "SELECT DISTINCT day " +
                "FROM voca_list"
    )
    suspend fun getAllDays(): List<Int>

    @Query(
        "SELECT * FROM voca_list " +
                "WHERE day = :day"
    )
    fun getVocaList(day: Int): Flow<List<Vocabulary>>

    @Query(
        "SELECT * FROM voca_list " +
                "WHERE bookmarked = :bookmarked"
    )
    fun getBookmarkList(bookmarked: Boolean = true): Flow<List<Vocabulary>>

    @Query(
        "SELECT * FROM voca_list " +
                "WHERE highlighted = :highlighted"
    )
    fun getHighlightList(highlighted: Boolean = true): Flow<List<Vocabulary>>

    @Query(
        "SELECT * FROM voca_list " +
                "WHERE vocaId = :vocaId"
    )
    suspend fun getVocabulary(vocaId: Int): Vocabulary

}
