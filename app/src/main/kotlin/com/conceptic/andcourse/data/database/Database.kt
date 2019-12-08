package com.conceptic.andcourse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.conceptic.andcourse.data.database.dao.FeatureDao
import com.conceptic.andcourse.data.database.dao.QuestionDao
import com.conceptic.andcourse.data.database.entity.FeatureEntity
import com.conceptic.andcourse.data.database.entity.QuestionEntity

@Database(
    entities = [
        QuestionEntity::class,
        FeatureEntity::class
    ], version = 1
)
@TypeConverters(TypeConverters::class)
abstract class Database : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    abstract fun featureDao(): FeatureDao
}