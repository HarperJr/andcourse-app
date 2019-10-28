package com.conceptic.andcourse.di

import com.conceptic.andcourse.BuildConfig
import com.conceptic.andcourse.data.api.auth.AuthApi
import com.conceptic.andcourse.data.api.auth.AuthApiExecutor
import com.conceptic.andcourse.data.api.auth.AuthApiExecutorImpl
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.api.questionnaire.QuestionnaireApi
import com.conceptic.andcourse.data.api.support.Interceptors
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiModule {
    operator fun invoke() = module {
        single { JwtTokenProvider(get()) }

        single {
            GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }

        single {
            OkHttpClient.Builder()
                .addInterceptor(Interceptors.loggingInterceptor())
                .addInterceptor(Interceptors.jwtTokenInterceptor(get()))
                .callTimeout(CALL_TIMEOUT, TimeUnit.MILLISECONDS)
                .build()
        }

        single {
            Retrofit.Builder()
                .client(get<OkHttpClient>())
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
        }

        /**
         * Api instances are declared here
         */
        single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }

        single<QuestionnaireApi> { get<Retrofit>().create(QuestionnaireApi::class.java) }

        /**
         * ApiExecutors instances are declared here
         */
        single<AuthApiExecutor> { AuthApiExecutorImpl(get()) }
    }

    private const val CALL_TIMEOUT = 100000L
}