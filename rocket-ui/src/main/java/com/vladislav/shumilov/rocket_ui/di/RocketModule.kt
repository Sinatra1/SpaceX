package com.vladislav.shumilov.rocket_ui.di

import com.example.payload_data.repository.PayloadLocalRepositoryImpl
import com.example.rocket_data.database.*
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
        firstStageLocalRepository: FirstStageLocalRepositoryImpl,
        secondStageDao: SecondStageDao
    ) =
        RocketLocalRepositoryImpl(rocketDao, firstStageLocalRepository, secondStageDao)

    @Provides
    @FragmentScope
    fun provideFirstStageRemoteRepository(coreRemoteRepository: CoreRemoteRepositoryImpl) =
        FirstStageRemoteRepositoryImpl(coreRemoteRepository)

    @Provides
    @FragmentScope
    fun provideFirstStageLocalRepository(
        firstStageDao: FirstStageDao,
        coreDao: CoreDao,
        firstStageToCoreDao: FirstStageToCoreDao
    ) =
        FirstStageLocalRepositoryImpl(firstStageDao, coreDao, firstStageToCoreDao)

    @Provides
    @FragmentScope
    fun provideSecondStageRemoteRepository(payloadRemoteRepository: PayloadRemoteRepositoryImpl) =
        SecondStageRemoteRepositoryImpl(payloadRemoteRepository)

    @Provides
    @FragmentScope
    fun provideSecondStageLocalRepository(
        secondStageDao: SecondStageDao,
        payloadLocalRepository: PayloadLocalRepositoryImpl,
        secondStageToPayloadDao: SecondStageToPayloadDao
    ) =
        SecondStageLocalRepositoryImpl(
            secondStageDao,
            payloadLocalRepository,
            secondStageToPayloadDao
        )

    @Provides
    @FragmentScope
    fun provideFairingsRemoteRepository() = FairingsRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideCoreRemoteRepository() = CoreRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun providePayloadRemoteRepository(orbitParamsRemoteRepository: OrbitParamsRemoteRepositoryImpl) =
        PayloadRemoteRepositoryImpl(orbitParamsRemoteRepository)

    @Provides
    @FragmentScope
    fun providePayloadLocalRepository(
        payloadDao: PayloadDao,
        orbitParamsDao: OrbitParamsDao
    ) =
        PayloadLocalRepositoryImpl(
            payloadDao,
            orbitParamsDao
        )

    @Provides
    @FragmentScope
    fun provideOrbitParamsRemoteRepository() = OrbitParamsRemoteRepositoryImpl()

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

    @Provides
    @FragmentScope
    fun provideCoreDao(@ApplicationScope appDatabase: AppDatabase): CoreDao =
        appDatabase.getCoreDao()

    @Provides
    @FragmentScope
    fun provideFirstStageToCoreDao(@ApplicationScope appDatabase: AppDatabase): FirstStageToCoreDao =
        appDatabase.getFirstStageToCoreDao()

    @Provides
    @FragmentScope
    fun provideSecondStageToPayloadDao(@ApplicationScope appDatabase: AppDatabase): SecondStageToPayloadDao =
        appDatabase.getSecondStageToPayloadDao()

    @Provides
    @FragmentScope
    fun providePayloadDao(@ApplicationScope appDatabase: AppDatabase): PayloadDao =
        appDatabase.getPayloadDao()


    @Provides
    @FragmentScope
    fun provideOrbitParamsDao(@ApplicationScope appDatabase: AppDatabase): OrbitParamsDao =
        appDatabase.getOrbitParamsDao()


}