package com.conceptic.andcourse.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Question")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val content: String
)