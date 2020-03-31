package com.vladislav.shumilov.rocket_ui.di

import com.example.rocket_data.database.FirstStageDao
import com.example.rocket_data.database.RocketDao
import com.example.rocket_data.database.SecondStageDao
import com.example.rocket_data.repository.*
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import dagger.Module
import dagger.Provides

@Module
@FragmentScope
class RocketModule {

    @Provides
    @FragmentScope
    fun provideRocketRemoteRepository(
        firstStageRemoteRepository: FirstStageRemoteRepositoryImpl,
        secondStageRemoteRepository: SecondStageRemoteRepositoryImpl,
        fairingsRemoteRepository: FairingsRemoteRepositoryImpl
    ) = RocketRemoteRepositoryImpl(
        firstStageRemoteRepository,
        secondStageRemoteRepository,
        fairingsRemoteRepository
    )

    @Provides
    @FragmentScope
    fun provideRocketLocalRepository(
        rocketDao: RocketDao,
        firstStageDao: FirstStageDao,
        secondStageDao: SecondStageDao
    ) =
        RocketLocalRepositoryImpl(rocketDao, firstStageDao, secondStageDao)

    @Provides
    @FragmentScope
    fun provideFirstStageRemoteRepository() = FirstStageRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideSecondStageRemoteRepository() = SecondStageRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideFairingsRemoteRepository() = FairingsRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideRocketDao(@ApplicationScope appDatabase: AppDatabase): RocketDao =
        appDatabase.getRocketDao()

    @Provides
    @FragmentScope
    fun provideFirstStageDao(@ApplicationScope appDatabase: AppDatabase): FirstStageDao =
        appDatabase.getFirstStageDao()

    @Provides
    @FragmentScope
    fun provideSecondStageDao(@ApplicationScope appDatabase: AppDatabase): SecondStageDao =
        appDatabase.getSecondStageDao()
}