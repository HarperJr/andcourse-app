package com.conceptic.andcourse.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Questions")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val order: Int,
    val content: String
)