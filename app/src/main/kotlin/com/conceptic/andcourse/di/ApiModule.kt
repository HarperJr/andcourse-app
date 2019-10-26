package com.conceptic.andcourse.di

import com.conceptic.andcourse.BuildConfig
import com.conceptic.andcourse.data.api.Interceptors
import com.conceptic.andcourse.data.api.JwtTokenProvider
import com.conceptic.andcourse.data.api.auth.AuthApi
import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

val ApiModule = module {
    single { JwtTokenProvider(get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor { chain -> Interceptors.interceptJwtToken(chain, get()) }
            .callTimeout(CALL_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
    }

    /**
     * Api instances are declared here
     */
    single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }

    single<QuestionnaireApi> { get<Retrofit>().create(QuestionnaireApi::class.java) }
}

private const val CALL_TIMEOUT = 100000L
