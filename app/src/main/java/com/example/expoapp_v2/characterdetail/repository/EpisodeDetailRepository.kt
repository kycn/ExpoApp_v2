package com.example.expoapp_v2.characterdetail.repository

import com.example.expoapp_v2.characterdetail.datasource.remote.EpisodeDetailRemoteDataSource
import com.example.expoapp_v2.characterdetail.service.model.EpisodeResult
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface EpisodeDetailRepository {
    fun getEpisode(id: Int): Flow<ApiResult<EpisodeResult>>
}

class DefaultEpisodeDetailRepository @Inject constructor(
    private val episodeDetailRemoteDataSource: EpisodeDetailRemoteDataSource
) : EpisodeDetailRepository {
    override fun getEpisode(id: Int): Flow<ApiResult<EpisodeResult>> {
        return episodeDetailRemoteDataSource.getEpisode(id)
    }
}