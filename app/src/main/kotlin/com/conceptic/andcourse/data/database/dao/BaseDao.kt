package com.conceptic.andcourse.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery

interface BaseDao<Entity, Id : Any> {

    @Insert
    suspend fun insert(entity: Entity)

    @Insert
    suspend fun insert(entities: List<Entity>)

    @Delete
    suspend fun delete(entity: Entity)

    @Update
    suspend fun update(entity: Entity)

    @RawQuery
    suspend fun raw(query: SupportSQLiteQuery): Any
}