package com.conceptic.andcourse.di

import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import com.conceptic.andcourse.usecase.auth.signup.SignUpCase
import com.conceptic.andcourse.usecase.questionnaire.BeginQuestionnaireCase
import com.conceptic.andcourse.usecase.questionnaire.NextQuestionCase
import org.koin.dsl.module

object UseCaseModule {
    operator fun invoke() = module {
        //Auth
        factory { SignInCase(get(), get()) }
        factory { SignUpCase(get()) }

        //Questionnaire
        factory { BeginQuestionnaireCase(get(), get()) }
        factory { NextQuestionCase(get()) }
    }
}