package com.conceptic.andcourse.data.repos

import com.conceptic.andcourse.data.database.dao.QuestionDao
import com.conceptic.andcourse.data.database.entity.QuestionEntity
import com.conceptic.andcourse.data.database.mapping.QuestionMapper
import com.conceptic.andcourse.data.model.Question

interface QuestionRepository : Repository<Question, String>

class QuestionRepositoryImpl(dao: QuestionDao) :
    BaseRepository<QuestionEntity, Question, String>(dao, QuestionMapper, QuestionEntity::class),
    QuestionRepository