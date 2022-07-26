package com.example.expoapp_v2.characterdetail.di

import com.example.expoapp_v2.characterdetail.service.EpisodeDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodeDetailModule {

    @Singleton
    @Provides
    fun providesEpisodeApi(retrofit: Retrofit): EpisodeDetailApi {
        return retrofit.create(EpisodeDetailApi::class.java)
    }
}
