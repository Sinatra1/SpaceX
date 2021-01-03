package com.vladislav.shumilov.rocket_ui.di

import com.example.payload_data.repository.PayloadLocalRepositoryImpl
import com.example.rocket_data.database.*
import com.example.rocket_data.repository.*
import com.example.rocket_domain.repository.*
import com.vladislav.shumilov.core_data.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
@FragmentScope
abstract class RocketModule {

    @Module
    @FragmentScope
    companion object {
        @Provides
        @JvmStatic
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
        @JvmStatic
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
        @JvmStatic
        @FragmentScope
        fun provideFirstStageRemoteRepository(
            coreRemoteRepository: CoreRemoteRepository
        ): FirstStageRemoteRepository =
            FirstStageRemoteRepositoryImpl(coreRemoteRepository)

        @Provides
        @JvmStatic
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
        @JvmStatic
        @FragmentScope
        fun provideSecondStageRemoteRepository(
            payloadRemoteRepository: PayloadRemoteRepository
        ): SecondStageRemoteRepository =
            SecondStageRemoteRepositoryImpl(payloadRemoteRepository)

        @Provides
        @JvmStatic
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
        @JvmStatic
        @FragmentScope
        fun providePayloadRemoteRepository(
            orbitParamsRemoteRepository: OrbitParamsRemoteRepository
        ): PayloadRemoteRepository =
            PayloadRemoteRepositoryImpl(orbitParamsRemoteRepository)

        @Provides
        @JvmStatic
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
        @JvmStatic
        @FragmentScope
        fun provideOrbitParamsLocalRepository(
            orbitParamsDao: OrbitParamsDao
        ): OrbitParamsLocalRepository =
            OrbitParamsLocalRepositoryImpl(orbitParamsDao)
    }

    @Binds
    @FragmentScope
    abstract fun bindFairingsRemoteRepository(impl: FairingsRemoteRepositoryImpl): FairingsRemoteRepository

    @Binds
    @FragmentScope
    abstract fun bindCoreRemoteRepository(impl: CoreRemoteRepositoryImpl): CoreRemoteRepository


    @Binds
    @FragmentScope
    abstract fun bindOrbitParamsRemoteRepository(impl: OrbitParamsRemoteRepositoryImpl): OrbitParamsRemoteRepository
}