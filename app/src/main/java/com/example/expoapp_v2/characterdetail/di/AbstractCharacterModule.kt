package com.example.expoapp_v2.characters.di

import com.example.expoapp_v2.characterdetail.datasource.remote.CharacterRemoteDataSource
import com.example.expoapp_v2.characterdetail.datasource.remote.DefaultCharacterRemoteDataSource
import com.example.expoapp_v2.characterdetail.repository.CharacterRepository
import com.example.expoapp_v2.characterdetail.repository.DefaultCharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractCharacterModule {

    @Singleton
    @Binds
    abstract fun bindsCharacterRemoteDataSource(defaultCharacterRemoteDataSource: DefaultCharacterRemoteDataSource): CharacterRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsCharacterRepository(defaultCharacterRepository: DefaultCharacterRepository): CharacterRepository
}