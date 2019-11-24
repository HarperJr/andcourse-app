package com.conceptic.andcourse.data.repos

import com.conceptic.andcourse.data.database.dao.QuestionDao
import com.conceptic.andcourse.data.database.mapping.QuestionMapper
import com.conceptic.andcourse.data.model.Question
import kotlinx.coroutines.coroutineScope

interface QuestionRepository {
    suspend fun storeQuestions(questions: List<Question>)
    suspend fun questions(): List<Question>
}

class QuestionRepositoryImpl(
    private val dao: QuestionDao
) : QuestionRepository {
    override suspend fun storeQuestions(questions: List<Question>) = coroutineScope {
        dao.drop()
        dao.insert(questions.map { QuestionMapper.toEntity(it) })
    }

    override suspend fun questions(): List<Question> = coroutineScope {
        dao.all().map { QuestionMapper.toModel(it) }
    }
}