package com.conceptic.andcourse.data.repos

import androidx.sqlite.db.SimpleSQLiteQuery
import com.conceptic.andcourse.data.database.dao.BaseDao
import com.conceptic.andcourse.data.database.mapping.Mapper
import io.reactivex.Single
import kotlin.reflect.KClass

/**
 *
 * @param dao - dao class, where E is entity, Id is id
 */
open class BaseRepository<Entity : Any, Model : Any, Id : Any>(
    private val dao: BaseDao<Entity, Id>,
    private val mapper: Mapper<Entity, Model>,
    private val entity: KClass<Entity>
) : Repository<Model, Id> {
    private val tableName by lazy {
        val entityAnnotation = entity.annotations
            .find { it.annotationClass == androidx.room.Entity::class } as androidx.room.Entity
        entityAnnotation.tableName
    }

    override fun findById(id: Id): Single<Model> =
        dao.rawRx(SimpleSQLiteQuery("SELECT * FROM $tableName WHERE id = :id LIMIT 1"))
            .map { mapper.toModel(it.first()) }

    override fun all(): Single<List<Model>> =
        dao.rawRx(SimpleSQLiteQuery("SELECT * FROM $tableName"))
            .map { mapper.toModel(it) }

    override fun insert(model: Model) = dao.insert(mapper.toEntity(model))

    override fun insert(models: List<Model>) = dao.insert(mapper.toEntity(models))

    override fun delete(model: Model) = dao.delete(mapper.toEntity(model))

    override fun deleteById(id: Id) {
        dao.raw(SimpleSQLiteQuery("DELETE FROM $tableName WHERE id = :id"))
    }

    override fun update(model: Model) = dao.update(mapper.toEntity(model))
}