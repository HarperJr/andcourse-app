package com.conceptic.andcourse.di

import com.conceptic.andcourse.SharedPreferencesProvider
import com.conceptic.andcourse.presentation.auth.signin.SignInFragment
import com.conceptic.andcourse.presentation.auth.signin.SignInViewModel
import com.conceptic.andcourse.presentation.auth.signup.SignUpFragment
import com.conceptic.andcourse.presentation.auth.signup.SignUpViewModel
import com.conceptic.andcourse.presentation.questionnaire.begin.QuestionnaireBeginFragment
import com.conceptic.andcourse.presentation.questionnaire.begin.QuestionnaireBeginViewModel
import com.conceptic.andcourse.presentation.questionnaire.question.QuestionFragment
import com.conceptic.andcourse.presentation.questionnaire.question.QuestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object AppModule {
    operator fun invoke() = module {
        single { SharedPreferencesProvider(get()) }

        scope(named<SignInFragment>()) {
            viewModel {
                SignInViewModel(get())
            }
        }

        scope(named<SignUpFragment>()) {
            viewModel {
                SignUpViewModel(get())
            }
        }

        scope(named<QuestionnaireBeginFragment>()) {
            viewModel {
                QuestionnaireBeginViewModel(get())
            }
        }

        scope(named<QuestionFragment>()) {
            viewModel {
                QuestionViewModel(get(), get())
            }
        }
    }
}
