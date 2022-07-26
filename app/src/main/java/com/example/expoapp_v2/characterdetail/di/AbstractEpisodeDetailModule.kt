package com.example.expoapp_v2.characterdetail.di

import com.example.expoapp_v2.characterdetail.datasource.remote.DefaultEpisodeDetailRemoteDataSource
import com.example.expoapp_v2.characterdetail.datasource.remote.EpisodeDetailRemoteDataSource
import com.example.expoapp_v2.characterdetail.repository.DefaultEpisodeDetailRepository
import com.example.expoapp_v2.characterdetail.repository.EpisodeDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractEpisodeDetailModule {

    @Singleton
    @Binds
    abstract fun bindsEpisodeRemoteDataSource(defaultEpisodeDetailRemoteDataSource: DefaultEpisodeDetailRemoteDataSource): EpisodeDetailRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsEpisodeRepository(defaultEpisodeDetailRepository: DefaultEpisodeDetailRepository): EpisodeDetailRepository
}