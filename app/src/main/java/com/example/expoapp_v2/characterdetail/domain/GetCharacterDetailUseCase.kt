package com.example.expoapp_v2.characterdetail.domain

import com.example.expoapp_v2.characterdetail.domain.mapper.toDomainModel
import com.example.expoapp_v2.characterdetail.domain.model.DetailedCharacter
import com.example.expoapp_v2.characterdetail.repository.CharacterDetailRepository
import com.example.expoapp_v2.common.di.IoDispatcher
import com.example.expoapp_v2.common.domain.FlowUseCase
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val characterDetailRepository: CharacterDetailRepository
) : FlowUseCase<GetCharacterDetailUseCase.GetCharacterUseCaseParams, DetailedCharacter>(dispatcher) {
    override fun execute(parameters: GetCharacterUseCaseParams): Flow<ApiResult<DetailedCharacter>> {
        return flow {
            characterDetailRepository.getCharacter(parameters.id).collect { apiResult ->
                if (apiResult is ApiResult.Success) {
                    emit(ApiResult.Success(apiResult.data.toDomainModel()))
                } else {
                    emit(ApiResult.Error(Exception("Api call was not successful")))
                }
            }
        }
    }

    data class GetCharacterUseCaseParams(
        val id: Int
    )
}