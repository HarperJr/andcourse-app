package com.conceptic.andcourse.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Statistics")
data class StatisticsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val chartViewType: Int,
    val name: String,
    val data: String
)