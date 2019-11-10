package com.conceptic.andcourse.data.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import io.reactivex.Single

interface BaseDao<Entity, Id : Any> {
    @Insert
    fun insert(entity: Entity)

    @Insert
    fun insert(entities: List<Entity>)

    @Delete
    fun delete(entity: Entity)

    @Update
    fun update(entity: Entity)

    @RawQuery
    fun rawRx(query: SupportSQLiteQuery): Single<List<Entity>>

    @RawQuery
    fun raw(query: SupportSQLiteQuery): Any
}