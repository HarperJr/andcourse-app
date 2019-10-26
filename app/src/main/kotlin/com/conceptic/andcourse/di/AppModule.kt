package com.conceptic.andcourse.di

import com.conceptic.andcourse.SharedPreferencesProvider
import com.conceptic.andcourse.presentation.auth.signin.SignInFragment
import com.conceptic.andcourse.presentation.auth.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val AppModule = module {
    single { SharedPreferencesProvider(get()) }

    scope(named<SignInFragment>()) {
        viewModel {
            SignInViewModel(get(), get())
        }
    }
}
