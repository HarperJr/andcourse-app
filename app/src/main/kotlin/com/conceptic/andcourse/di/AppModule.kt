package com.conceptic.andcourse.di

import com.conceptic.andcourse.presentation.auth.AuthFragment
import com.conceptic.andcourse.presentation.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModule {
    val instance = module {
        scope(named<AuthFragment>()) {
            viewModel {
                AuthViewModel()
            }
        }
    }
}