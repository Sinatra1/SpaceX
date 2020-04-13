package com.vladislav.shumilov.rocket_ui.di

import com.example.payload_data.repository.PayloadLocalRepositoryImpl
import com.example.rocket_data.database.*
import com.example.rocket_data.repository.*
import com.example.rocket_domain.repository.*
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import dagger.Module
import dagger.Provides

@Module
@FragmentScope
class RocketModule {

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideRocketRemoteRepository(
        firstStageRemoteRepository: FirstStageRemoteRepositoryAlias,
        secondStageRemoteRepository: SecondStageRemoteRepositoryAlias,
        fairingsRemoteRepository: FairingsRemoteRepositoryAlias
    ) = RocketRemoteRepositoryImpl(
        firstStageRemoteRepository,
        secondStageRemoteRepository,
        fairingsRemoteRepository
    ) as RocketRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideRocketLocalRepository(
        rocketDao: RocketDao,
        firstStageLocalRepository: FirstStageLocalRepositoryAlias,
        secondStageLocalRepository: SecondStageLocalRepositoryAlias,
        fairingsDao: FairingsDao
    ) =
        RocketLocalRepositoryImpl(
            rocketDao,
            firstStageLocalRepository,
            secondStageLocalRepository,
            fairingsDao
        ) as RocketLocalRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideFirstStageRemoteRepository(coreRemoteRepository: CoreRemoteRepositoryAlias) =
        FirstStageRemoteRepositoryImpl(coreRemoteRepository) as FirstStageRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideFirstStageLocalRepository(
        firstStageDao: FirstStageDao,
        coreDao: CoreDao,
        firstStageToCoreDao: FirstStageToCoreDao
    ) =
        FirstStageLocalRepositoryImpl(
            firstStageDao,
            coreDao,
            firstStageToCoreDao
        ) as FirstStageLocalRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideSecondStageRemoteRepository(payloadRemoteRepository: PayloadRemoteRepositoryAlias) =
        SecondStageRemoteRepositoryImpl(payloadRemoteRepository) as SecondStageRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideSecondStageLocalRepository(
        secondStageDao: SecondStageDao,
        payloadLocalRepository: PayloadLocalRepositoryAlias,
        secondStageToPayloadDao: SecondStageToPayloadDao
    ) =
        SecondStageLocalRepositoryImpl(
            secondStageDao,
            payloadLocalRepository,
            secondStageToPayloadDao
        ) as SecondStageLocalRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideFairingsRemoteRepository() =
        FairingsRemoteRepositoryImpl() as FairingsRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideCoreRemoteRepository() = CoreRemoteRepositoryImpl() as CoreRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun providePayloadRemoteRepository(orbitParamsRemoteRepository: OrbitParamsRemoteRepositoryAlias) =
        PayloadRemoteRepositoryImpl(orbitParamsRemoteRepository) as PayloadRemoteRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun providePayloadLocalRepository(
        payloadDao: PayloadDao,
        orbitParamsDao: OrbitParamsDao
    ) =
        PayloadLocalRepositoryImpl(
            payloadDao,
            orbitParamsDao
        ) as PayloadLocalRepositoryAlias

    @Suppress("UNCHECKED_CAST")
    @Provides
    @FragmentScope
    fun provideOrbitParamsRemoteRepository() =
        OrbitParamsRemoteRepositoryImpl() as OrbitParamsRemoteRepositoryAlias

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

    @Provides
    @FragmentScope
    fun provideFairingsDao(@ApplicationScope appDatabase: AppDatabase): FairingsDao =
        appDatabase.getFairingsDao()

}