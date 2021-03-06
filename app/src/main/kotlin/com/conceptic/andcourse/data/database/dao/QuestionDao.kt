package com.conceptic.andcourse.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conceptic.andcourse.data.database.entity.QuestionEntity

@Dao
abstract class QuestionDao : BaseDao<QuestionEntity, String> {
    @Query("SELECT * FROM Questions ORDER BY `order`")
    abstract suspend fun all(): List<QuestionEntity>

    @Query("DELETE FROM Questions")
    abstract suspend fun drop()

    @Query("SELECT * FROM Questions WHERE id = :id")
    abstract suspend fun find(id: String): QuestionEntity?
}