package com.conceptic.andcourse.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conceptic.andcourse.data.database.entity.StatisticsEntity

@Dao
abstract class StatisticsDao : BaseDao<StatisticsEntity, Int> {
    @Query("DELETE FROM Statistics")
    abstract suspend fun drop()

    @Query("SELECT * FROM Statistics")
    abstract suspend fun statistics(): List<StatisticsEntity>

}