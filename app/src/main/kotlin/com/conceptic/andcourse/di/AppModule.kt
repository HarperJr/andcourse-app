package com.conceptic.andcourse.di

import com.conceptic.andcourse.presentation.MainActivity
import com.conceptic.andcourse.presentation.auth.AuthViewModel
import org.koin.android.experimental.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModule {
    val instance = module {
        scope(named<MainActivity>()) {
            viewModel<AuthViewModel>()
        }
    }
}