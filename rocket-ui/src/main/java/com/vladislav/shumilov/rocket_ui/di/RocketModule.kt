package com.vladislav.shumilov.rocket_ui.di

import com.example.rocket_data.repository.FairingsRemoteRepositoryImpl
import com.example.rocket_data.repository.FirstStageRemoteRepositoryImpl
import com.example.rocket_data.repository.RocketRemoteRepositoryImpl
import com.example.rocket_data.repository.SecondStageRemoteRepositoryImpl
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
    ) = RocketRemoteRepositoryImpl(firstStageRemoteRepository, secondStageRemoteRepository, fairingsRemoteRepository)

    @Provides
    @FragmentScope
    fun provideFirstStageRemoteRepository() = FirstStageRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideSecondStageRemoteRepository() = SecondStageRemoteRepositoryImpl()

    @Provides
    @FragmentScope
    fun provideFairingsRemoteRepository() = FairingsRemoteRepositoryImpl()
}