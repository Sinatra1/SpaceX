package com.vladislav.shumilov.spacex.di

import android.content.Context
import androidx.room.Room
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.ApplicationScope
import dagger.Module
import dagger.Provides

private const val DATABASE_NAME = "spacex_database"

@Module
internal object DatabaseModule {

    @Provides
    @JvmStatic
    @ApplicationScope
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
}
