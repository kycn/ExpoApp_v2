package com.example.expoapp_v2.characters.domain

import com.example.expoapp_v2.characterdetail.domain.mapper.toDomainModel
import com.example.expoapp_v2.characterdetail.domain.model.DetailedCharacter
import com.example.expoapp_v2.characterdetail.repository.CharacterRepository
import com.example.expoapp_v2.common.di.IoDispatcher
import com.example.expoapp_v2.common.domain.FlowUseCase
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val characterRepository: CharacterRepository
) : FlowUseCase<GetCharacterUseCase.GetCharacterUseCaseParams, DetailedCharacter>(dispatcher) {
    override fun execute(parameters: GetCharacterUseCaseParams): Flow<ApiResult<DetailedCharacter>> {
        return flow {
            characterRepository.getCharacter(parameters.id).collect { apiResult ->
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