package com.conceptic.andcourse.data.repos

import io.reactivex.Single

interface Repository<Model, Id: Any> {
    fun findById(id: Id): Single<Model>

    fun all(): Single<List<Model>>

    fun insert(model: Model)

    fun insert(models: List<Model>)

    fun delete(model: Model)

    fun deleteById(id: Id)

    fun update(model: Model)
}