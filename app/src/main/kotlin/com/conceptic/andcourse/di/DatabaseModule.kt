package com.conceptic.andcourse.di

import androidx.room.Room
import com.conceptic.andcourse.data.database.Database
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
    }
}