package com.conceptic.andcourse.di

import com.conceptic.andcourse.usecase.auth.credentials.CredentialsCase
import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import com.conceptic.andcourse.usecase.auth.signup.SignUpCase
import com.conceptic.andcourse.usecase.questionnaire.intro.BeginQuestionnaireCase
import com.conceptic.andcourse.usecase.questionnaire.completion.CompleteQuestionnaireCase
import com.conceptic.andcourse.usecase.questionnaire.next.NextQuestionCase
import com.conceptic.andcourse.usecase.questionnaire.summary.SummaryCase
import com.conceptic.andcourse.usecase.statistics.StatisticsCase
import org.koin.dsl.module

object UseCaseModule {
    operator fun invoke() = module {
        //Auth
        factory { SignInCase(get(), get()) }

        factory { SignUpCase(get()) }

        factory { CredentialsCase(get()) }

        //Questionnaire
        factory { BeginQuestionnaireCase(get(), get()) }

        factory { NextQuestionCase(get()) }

        factory { CompleteQuestionnaireCase(get(), get()) }

        factory { SummaryCase(get(), get()) }

        factory { StatisticsCase(get(), get()) }
    }
}