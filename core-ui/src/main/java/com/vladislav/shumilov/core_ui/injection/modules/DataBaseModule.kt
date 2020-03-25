package com.vladislav.shumilov.core_ui.injection.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import androidx.room.Room
import com.vladislav.shumilov.core_data.ApplicationContext
import com.vladislav.shumilov.core_data.database.LocalDatabase
import com.vladislav.shumilov.core_data.ApplicationScope

private const val DATABASE_NAME = "spacex_database"

@Module
@ApplicationScope
class DataBaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase =
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries().build()
}