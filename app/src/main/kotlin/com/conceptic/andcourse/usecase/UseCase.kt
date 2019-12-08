package com.conceptic.andcourse.usecase

interface UseCase<P, R> {
    suspend fun execute(param: P): R
}