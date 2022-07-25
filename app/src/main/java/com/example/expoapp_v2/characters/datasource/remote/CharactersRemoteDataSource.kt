package com.example.expoapp_v2.characters.datasource.remote

import com.example.expoapp_v2.characters.service.CharactersApi
import com.example.expoapp_v2.characters.service.model.CharactersResult
import com.example.expoapp_v2.common.datasource.remote.BaseRemoteDataSource
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharactersRemoteDataSource {
    fun getCharacters(): Flow<ApiResult<CharactersResult>>
}

class DefaultCharactersRemoteDataSource @Inject constructor(
    private val charactersApi: CharactersApi
) : BaseRemoteDataSource(), CharactersRemoteDataSource {
    override fun getCharacters(): Flow<ApiResult<CharactersResult>> {
        return getResultFromApi { charactersApi.getCharacters() }
    }
}