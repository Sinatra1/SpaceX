package com.vladislav.shumilov.rocket_ui.di

import com.example.payload_data.repository.PayloadLocalRepositoryImpl
import com.example.rocket_data.database.*
import com.example.rocket_data.repository.*
import com.example.rocket_domain.repository.*
import com.vladislav.shumilov.core_data.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class RocketModule {

    @Provides
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
    fun provideFirstStageRemoteRepository(
        coreRemoteRepository: CoreRemoteRepository
    ): FirstStageRemoteRepository =
        FirstStageRemoteRepositoryImpl(coreRemoteRepository)

    @Provides
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
    fun provideSecondStageRemoteRepository(
        payloadRemoteRepository: PayloadRemoteRepository
    ): SecondStageRemoteRepository =
        SecondStageRemoteRepositoryImpl(payloadRemoteRepository)

    @Provides
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
    fun provideFairingsRemoteRepository(): FairingsRemoteRepository =
        FairingsRemoteRepositoryImpl()

    @Provides
    fun provideCoreRemoteRepository(): CoreRemoteRepository = CoreRemoteRepositoryImpl()

    @Provides
    fun providePayloadRemoteRepository(
        orbitParamsRemoteRepository: OrbitParamsRemoteRepository
    ): PayloadRemoteRepository =
        PayloadRemoteRepositoryImpl(orbitParamsRemoteRepository)

    @Provides
    fun providePayloadLocalRepository(
        payloadDao: PayloadDao,
        orbitParamsLocalRepository: OrbitParamsLocalRepository
    ): PayloadLocalRepository =
        PayloadLocalRepositoryImpl(
            payloadDao,
            orbitParamsLocalRepository
        )

    @Provides
    fun provideOrbitParamsRemoteRepository(): OrbitParamsRemoteRepository =
        OrbitParamsRemoteRepositoryImpl()

    @Provides
    fun provideOrbitParamsLocalRepository(
        orbitParamsDao: OrbitParamsDao
    ): OrbitParamsLocalRepository =
        OrbitParamsLocalRepositoryImpl(orbitParamsDao)
}