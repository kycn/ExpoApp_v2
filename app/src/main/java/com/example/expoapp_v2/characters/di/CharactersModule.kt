package com.example.expoapp_v2.characters.di

import com.example.expoapp_v2.characters.datasource.remote.CharactersRemoteDataSource
import com.example.expoapp_v2.characters.datasource.remote.DefaultCharactersRemoteDataSource
import com.example.expoapp_v2.characters.repository.CharactersRepository
import com.example.expoapp_v2.characters.repository.DefaultCharactersRepository
import com.example.expoapp_v2.characters.service.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object CharactersModule {

    @ActivityScoped
    @Provides
    fun providesCharactersApi(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }

    @ActivityScoped
    @Provides
    fun providesCharactersRemoteDataSource(api: CharactersApi) : CharactersRemoteDataSource =
        DefaultCharactersRemoteDataSource(api)

    @ActivityScoped
    @Provides
    fun providesCharactersRepository(charactersRemoteDataSource: CharactersRemoteDataSource) : CharactersRepository =
        DefaultCharactersRepository(charactersRemoteDataSource)

}
