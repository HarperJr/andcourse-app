package com.conceptic.andcourse.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conceptic.andcourse.data.database.entity.QuestionEntity

@Dao
abstract class QuestionDao : BaseDao<QuestionEntity, String> {
    @Query("SELECT * FROM Questions")
    abstract suspend fun all(): List<QuestionEntity>
}