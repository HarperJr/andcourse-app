package com.conceptic.andcourse.data.database.dao

import androidx.room.Dao
import com.conceptic.andcourse.data.database.entity.QuestionEntity

@Dao
interface QuestionDao : BaseDao<QuestionEntity, String>