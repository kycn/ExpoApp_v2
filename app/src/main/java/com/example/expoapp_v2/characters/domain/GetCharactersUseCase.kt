package com.example.expoapp_v2.characters.domain

import com.example.expoapp_v2.characters.domain.model.CharacterItem
import com.example.expoapp_v2.characters.domain.model.mapper.toDomainModel
import com.example.expoapp_v2.characters.repository.CharactersRepository
import com.example.expoapp_v2.common.di.IoDispatcher
import com.example.expoapp_v2.common.domain.FlowUseCase
import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val charactersRepository: CharactersRepository
) :
    FlowUseCase<Unit, List<CharacterItem>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<ApiResult<List<CharacterItem>>> {
        return flow {
            charactersRepository.getCharacters().collect { apiResult ->
                if (apiResult is ApiResult.Success) {
                    emit(ApiResult.Success(apiResult.data.results.map { it.toDomainModel() }))
                } else {
                    emit(ApiResult.Error(Exception("Api call was not successful")))
                }
            }
        }
    }
}