package com.example.expoapp_v2.characterdetail.repository

import com.example.expoapp_v2.characterdetail.datasource.remote.CharacterDetailRemoteDataSource
import com.example.expoapp_v2.characterdetail.service.model.CharacterResult
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharacterDetailRepository {
    fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>>
}

class DefaultCharacterDetailRepository @Inject constructor(
    private val characterDetailRemoteDataSource: CharacterDetailRemoteDataSource
) : CharacterDetailRepository {
    override fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>> {
        return characterDetailRemoteDataSource.getCharacter(id)
    }
}