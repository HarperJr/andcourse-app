package com.conceptic.andcourse.di

import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import org.koin.dsl.module

val UseCaseModule = module {
    single { SignInCase(get()) }
}