package com.conceptic.andcourse.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Features")
data class FeatureEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: Int,
    val description: String,
    val points: Int
)