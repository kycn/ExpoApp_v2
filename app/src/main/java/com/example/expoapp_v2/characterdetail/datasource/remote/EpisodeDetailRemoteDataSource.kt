package com.example.expoapp_v2.characterdetail.datasource.remote

import com.example.expoapp_v2.characterdetail.service.EpisodeDetailApi
import com.example.expoapp_v2.characterdetail.service.model.EpisodeResult
import com.example.expoapp_v2.common.datasource.remote.BaseRemoteDataSource
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface EpisodeDetailRemoteDataSource {
    fun getEpisode(id: Int): Flow<ApiResult<EpisodeResult>>
}

class DefaultEpisodeDetailRemoteDataSource @Inject constructor(
    private val episodeDetailApi: EpisodeDetailApi
) : BaseRemoteDataSource(), EpisodeDetailRemoteDataSource {
    override fun getEpisode(id: Int): Flow<ApiResult<EpisodeResult>> {
        return getResultFromApi { episodeDetailApi.getEpisode(id) }
    }
}