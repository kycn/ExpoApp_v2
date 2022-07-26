package com.example.expoapp_v2.characterdetail.repository

import com.example.expoapp_v2.characterdetail.datasource.remote.CharacterRemoteDataSource
import com.example.expoapp_v2.characterdetail.service.model.CharacterResult
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharacterRepository {
    fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>>
}

class DefaultCharacterRepository @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource
) : CharacterRepository {
    override fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>> {
        return characterRemoteDataSource.getCharacter(id)
    }
}