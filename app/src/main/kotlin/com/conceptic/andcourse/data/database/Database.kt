package com.conceptic.andcourse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.conceptic.andcourse.data.database.dao.QuestionDao
import com.conceptic.andcourse.data.database.entity.QuestionEntity

@Database(
    entities = [
        QuestionEntity::class
    ], version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}