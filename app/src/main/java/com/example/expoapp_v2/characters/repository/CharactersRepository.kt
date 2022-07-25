package com.example.expoapp_v2.characters.repository

import com.example.expoapp_v2.characters.datasource.remote.CharactersRemoteDataSource
import com.example.expoapp_v2.characters.service.model.CharactersResult
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharactersRepository {
    fun getCharacters(): Flow<ApiResult<CharactersResult>>
}

class DefaultCharactersRepository @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override fun getCharacters(): Flow<ApiResult<CharactersResult>> {
        return charactersRemoteDataSource.getCharacters()
    }
}