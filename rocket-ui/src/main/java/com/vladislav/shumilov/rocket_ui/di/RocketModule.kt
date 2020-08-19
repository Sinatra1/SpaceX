package com.vladislav.shumilov.rocket_ui.di

import com.example.payload_data.repository.PayloadLocalRepositoryImpl
import com.example.rocket_data.database.*
import com.example.rocket_data.repository.*
import com.example.rocket_domain.repository.*
import com.vladislav.shumilov.core_data.FragmentScope
import dagger.Module
import dagger.Provides

@Module
@FragmentScope
class RocketModule {

    @Provides
    @FragmentScope
    fun provideRocketRemoteRepository(
        firstStageRemoteRepository: FirstStageRemoteRepository,
        secondStageRemoteRepository: SecondStageRemoteRepository,
        fairingsRemoteRepository: FairingsRemoteRepository
    ): RocketRemoteRepository = RocketRemoteRepositoryImpl(
        firstStageRemoteRepository,
        secondStageRemoteRepository,
        fairingsRemoteRepository
    )

    @Provides
    @FragmentScope
    fun provideRocketLocalRepository(
        rocketDao: RocketDao,
        firstStageLocalRepository: FirstStageLocalRepository,
        secondStageLocalRepository: SecondStageLocalRepository,
        fairingsDao: FairingsDao
    ): RocketLocalRepository =
        RocketLocalRepositoryImpl(
            rocketDao,
            firstStageLocalRepository,
            secondStageLocalRepository,
            fairingsDao
        )

    @Provides
    @FragmentScope
    fun provideFirstStageRemoteRepository(
        coreRemoteRepository: CoreRemoteRepository
    ): FirstStageRemoteRepository =
        FirstStageRemoteRepositoryImpl(coreRemoteRepository)

    @Provides
    @FragmentScope
    fun provideFirstStageLocalRepository(
        firstStageDao: FirstStageDao,
        coreDao: CoreDao,
        firstStageToCoreDao: FirstStageToCoreDao
    ): FirstStageLocalRepository =
        FirstStageLocalRepositoryImpl(
            firstStageDao,
            coreDao,
            firstStageToCoreDao
        )

    @Provides
    @FragmentScope
    fun provideSecondStageRemoteRepository(
        payloadRemoteRepository: PayloadRemoteRepository
    ): SecondStageRemoteRepository =
        SecondStageRemoteRepositoryImpl(payloadRemoteRepository)

    @Provides
    @FragmentScope
    fun provideSecondStageLocalRepository(
        secondStageDao: SecondStageDao,
        payloadLocalRepository: PayloadLocalRepository,
        secondStageToPayloadDao: SecondStageToPayloadDao
    ): SecondStageLocalRepository =
        SecondStageLocalRepositoryImpl(
            secondStageDao,
            payloadLocalRepository,
            secondStageToPayloadDao
        )

    @Provides
    @FragmentScope
    fun provideFairingsRemoteRepository(): FairingsRemoteRepository =
        FairingsRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideCoreRemoteRepository(): CoreRemoteRepository = CoreRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun providePayloadRemoteRepository(
        orbitParamsRemoteRepository: OrbitParamsRemoteRepository
    ): PayloadRemoteRepository =
        PayloadRemoteRepositoryImpl(orbitParamsRemoteRepository)

    @Provides
    @FragmentScope
    fun providePayloadLocalRepository(
        payloadDao: PayloadDao,
        orbitParamsLocalRepository: OrbitParamsLocalRepository
    ): PayloadLocalRepository =
        PayloadLocalRepositoryImpl(
            payloadDao,
            orbitParamsLocalRepository
        )

    @Provides
    @FragmentScope
    fun provideOrbitParamsRemoteRepository(): OrbitParamsRemoteRepository =
        OrbitParamsRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideOrbitParamsLocalRepository(
        orbitParamsDao: OrbitParamsDao
    ): OrbitParamsLocalRepository =
        OrbitParamsLocalRepositoryImpl(orbitParamsDao)
}