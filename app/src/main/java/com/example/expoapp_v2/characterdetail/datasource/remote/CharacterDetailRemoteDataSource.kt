package com.example.expoapp_v2.characterdetail.datasource.remote

import com.example.expoapp_v2.characterdetail.service.CharacterDetailApi
import com.example.expoapp_v2.characterdetail.service.model.CharacterResult
import com.example.expoapp_v2.common.datasource.remote.BaseRemoteDataSource
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CharacterDetailRemoteDataSource {
    fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>>
}

class DefaultCharacterDetailRemoteDataSource @Inject constructor(
    private val characterDetailApi: CharacterDetailApi
) : BaseRemoteDataSource(), CharacterDetailRemoteDataSource {
    override fun getCharacter(id: Int): Flow<ApiResult<CharacterResult>> {
        return getResultFromApi { characterDetailApi.getCharacter(id) }
    }
}