package com.vladislav.shumilov.rocket_ui.di

import com.example.payload_data.repository.PayloadLocalRepositoryImpl
import com.example.rocket_data.database.*
import com.example.rocket_data.repository.*
import com.example.rocket_domain.repository.*
import com.vladislav.shumilov.app_data.database.AppDatabase
import com.vladislav.shumilov.core_data.ApplicationScope
import com.vladislav.shumilov.core_data.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RocketModule {

    @Module
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

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideRocketDao(@ApplicationScope appDatabase: AppDatabase): RocketDao =
            appDatabase.getRocketDao()

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideFirstStageDao(@ApplicationScope appDatabase: AppDatabase): FirstStageDao =
            appDatabase.getFirstStageDao()

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideSecondStageDao(@ApplicationScope appDatabase: AppDatabase): SecondStageDao =
            appDatabase.getSecondStageDao()

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideCoreDao(@ApplicationScope appDatabase: AppDatabase): CoreDao =
            appDatabase.getCoreDao()

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideFirstStageToCoreDao(@ApplicationScope appDatabase: AppDatabase): FirstStageToCoreDao =
            appDatabase.getFirstStageToCoreDao()

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideSecondStageToPayloadDao(@ApplicationScope appDatabase: AppDatabase): SecondStageToPayloadDao =
            appDatabase.getSecondStageToPayloadDao()

        @Provides
        @JvmStatic
        @FragmentScope
        fun providePayloadDao(@ApplicationScope appDatabase: AppDatabase): PayloadDao =
            appDatabase.getPayloadDao()


        @Provides
        @JvmStatic
        @FragmentScope
        fun provideOrbitParamsDao(@ApplicationScope appDatabase: AppDatabase): OrbitParamsDao =
            appDatabase.getOrbitParamsDao()

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideFairingsDao(@ApplicationScope appDatabase: AppDatabase): FairingsDao =
            appDatabase.getFairingsDao()
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