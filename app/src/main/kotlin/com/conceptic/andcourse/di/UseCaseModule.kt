package com.conceptic.andcourse.di

import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import org.koin.dsl.module

object UseCaseModule {
    operator fun invoke() = module {
        single { SignInCase(get()) }
    }
}