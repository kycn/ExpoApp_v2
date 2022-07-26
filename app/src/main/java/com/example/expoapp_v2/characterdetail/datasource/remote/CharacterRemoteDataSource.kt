package com.example.expoapp_v2.characterdetail.datasource.remote

import com.example.expoapp_v2.characterdetail.service.CharacterApi
import com.example.expoapp_v2.characterdetail.service.model.CharacterResult
import com.example.expoapp_v2.common.datasource.remote.BaseRemoteDataSource
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharacterRemoteDataSource {
    fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>>
}

class DefaultCharacterRemoteDataSource @Inject constructor(
    private val characterApi: CharacterApi
) : BaseRemoteDataSource(), CharacterRemoteDataSource {
    override fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>> {
        return getResultFromApi { characterApi.getCharacter(id) }
    }
}