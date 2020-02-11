package com.conceptic.andcourse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.conceptic.andcourse.data.database.dao.FeatureDao
import com.conceptic.andcourse.data.database.dao.QuestionDao
import com.conceptic.andcourse.data.database.dao.StatisticsDao
import com.conceptic.andcourse.data.database.entity.FeatureEntity
import com.conceptic.andcourse.data.database.entity.QuestionEntity
import com.conceptic.andcourse.data.database.entity.StatisticsEntity

@Database(
    entities = [
        QuestionEntity::class,
        FeatureEntity::class,
        StatisticsEntity::class
    ], version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    abstract fun featureDao(): FeatureDao

    abstract fun statisticsDao(): StatisticsDao
}