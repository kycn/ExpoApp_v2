package com.example.expoapp_v2.characterdetail.di

import com.example.expoapp_v2.characterdetail.datasource.remote.CharacterDetailRemoteDataSource
import com.example.expoapp_v2.characterdetail.datasource.remote.DefaultCharacterDetailRemoteDataSource
import com.example.expoapp_v2.characterdetail.repository.CharacterDetailRepository
import com.example.expoapp_v2.characterdetail.repository.DefaultCharacterDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractCharacterDetailModule {

    @Singleton
    @Binds
    abstract fun bindsCharacterRemoteDataSource(defaultCharacterRemoteDataSource: DefaultCharacterDetailRemoteDataSource): CharacterDetailRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsCharacterRepository(defaultCharacterRepository: DefaultCharacterDetailRepository): CharacterDetailRepository
}