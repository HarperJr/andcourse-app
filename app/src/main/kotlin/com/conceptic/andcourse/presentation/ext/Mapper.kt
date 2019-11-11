package com.conceptic.andcourse.presentation.ext

import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

interface Mapper<T, E> {
    fun map(t: T): E
}

inline fun <reified T : Any, reified E : Any> mapper(): Mapper<T, E> {
    return object : Mapper<T, E> {
        override fun map(t: T): E {
            val tClassProps = T::class.memberProperties.associateBy { it.name }
            if (E::class.isData) {
                val ePrimaryConstructor = E::class.primaryConstructor
                    ?: throw IllegalStateException("At least one constructor must be declared in class ${E::class.simpleName}")
                val eClassProps = ePrimaryConstructor.parameters.map { param ->
                    val tClassProp = tClassProps[param.name]
                        ?: throw IllegalStateException("Property ${param.name} doesn't exist in class ${T::class.simpleName}")
                    tClassProp.get(t)
                }
                return ePrimaryConstructor.call(eClassProps)
            } else throw IllegalStateException("Passed class type should be data class")
        }
    }
}