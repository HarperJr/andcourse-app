package com.conceptic.andcourse.di

import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import com.conceptic.andcourse.usecase.auth.signup.SignUpCase
import org.koin.dsl.module

object UseCaseModule {
    operator fun invoke() = module {
        factory { SignInCase(get()) }
        factory { SignUpCase(get()) }
    }
}