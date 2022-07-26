package com.example.expoapp_v2.characterdetail.di

import com.example.expoapp_v2.characterdetail.service.CharacterDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharacterDetailModule {

    @Singleton
    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharacterDetailApi {
        return retrofit.create(CharacterDetailApi::class.java)
    }
}
