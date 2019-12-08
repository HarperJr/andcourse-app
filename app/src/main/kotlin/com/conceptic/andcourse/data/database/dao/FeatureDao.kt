package com.conceptic.andcourse.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conceptic.andcourse.data.database.entity.FeatureEntity

@Dao
abstract class FeatureDao : BaseDao<FeatureEntity, Int> {
    @Query("SELECT * FROM Features ORDER BY type")
    abstract suspend fun all(): List<FeatureEntity>

    @Query("DELETE FROM Features")
    abstract suspend fun drop()
}