package com.example.expoapp_v2.characters.di

import com.example.expoapp_v2.characters.datasource.remote.CharactersRemoteDataSource
import com.example.expoapp_v2.characters.datasource.remote.DefaultCharactersRemoteDataSource
import com.example.expoapp_v2.characters.repository.CharactersRepository
import com.example.expoapp_v2.characters.repository.DefaultCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractCharactersModule {

    @Singleton
    @Binds
    abstract fun bindsCharactersRemoteDataSource(defaultCharactersRemoteDataSource: DefaultCharactersRemoteDataSource): CharactersRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindsCharactersRepository(defaultCharactersRepository: DefaultCharactersRepository): CharactersRepository
}