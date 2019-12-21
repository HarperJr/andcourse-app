package com.conceptic.andcourse.di

import androidx.room.Room
import com.conceptic.andcourse.data.database.Database
import com.conceptic.andcourse.data.repos.*
import org.koin.dsl.module

object DatabaseModule {
    private const val DATABASE_NAME = "questioonaire_db"

    operator fun invoke() = module {
        single {
            Room.databaseBuilder(get(), Database::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        /**
         * Dao are declared here
         */
        factory { get<Database>().questionDao() }

        factory { get<Database>().featureDao() }

        factory { get<Database>().statisticsDao() }

        /**
         * Repositories are declared here
         */
        factory<QuestionRepository> { QuestionRepositoryImpl(get()) }

        factory<SummaryRepository> { SummaryRepositoryImpl(get()) }

        factory<StatisticsRepository> { StatisticsRepositoryImpl(get()) }
    }
}