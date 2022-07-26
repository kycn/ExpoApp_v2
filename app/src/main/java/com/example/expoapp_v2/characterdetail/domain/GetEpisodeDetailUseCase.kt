package com.example.expoapp_v2.characterdetail.domain

import com.example.expoapp_v2.characterdetail.domain.mapper.toDomainModel
import com.example.expoapp_v2.characterdetail.domain.model.DetailedEpisode
import com.example.expoapp_v2.characterdetail.repository.EpisodeDetailRepository
import com.example.expoapp_v2.common.di.IoDispatcher
import com.example.expoapp_v2.common.domain.FlowUseCase
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEpisodeDetailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val episodeDetailRepository: EpisodeDetailRepository
) : FlowUseCase<GetEpisodeDetailUseCase.GetEpisodeDetailUseCaseParams, DetailedEpisode>(dispatcher) {
    override fun execute(parameters: GetEpisodeDetailUseCaseParams): Flow<ApiResult<DetailedEpisode>> {
        return flow {
            episodeDetailRepository.getEpisode(parameters.episodeId).collect { apiResult ->
                if (apiResult is ApiResult.Success) {
                    emit(ApiResult.Success(apiResult.data.toDomainModel()))
                } else {
                    emit(ApiResult.Error(Exception("Api call was not successful")))
                }
            }
        }
    }

    data class GetEpisodeDetailUseCaseParams(
        val episodeId: Int
    )
}