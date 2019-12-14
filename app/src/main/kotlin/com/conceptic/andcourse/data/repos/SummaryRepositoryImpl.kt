package com.conceptic.andcourse.data.repos

import com.conceptic.andcourse.data.database.dao.FeatureDao
import com.conceptic.andcourse.data.database.mapping.FeatureMapper
import com.conceptic.andcourse.data.model.Feature
import kotlinx.coroutines.coroutineScope

interface SummaryRepository {
    suspend fun drop()
    suspend fun store(features: List<Feature>)
    suspend fun features(): List<Feature>
}

class SummaryRepositoryImpl(
    private val dao: FeatureDao
) : SummaryRepository {
    override suspend fun drop() = coroutineScope {
        dao.drop()
    }

    override suspend fun store(features: List<Feature>) = coroutineScope {
        dao.insert(features.map { FeatureMapper.toEntity(it) })
    }

    override suspend fun features(): List<Feature> = coroutineScope {
        dao.all().map { FeatureMapper.toModel(it) }
    }
}