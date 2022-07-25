package com.example.expoapp_v2.common.domain

import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {
    operator fun invoke(parameters: P): Flow<ApiResult<R>> = execute(parameters)
        .catch { e -> emit(ApiResult.Error(Exception(e))) }
        .flowOn(dispatcher)

    protected abstract fun execute(parameters: P): Flow<ApiResult<R>>
}