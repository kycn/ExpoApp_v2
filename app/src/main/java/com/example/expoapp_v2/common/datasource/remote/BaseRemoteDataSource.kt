package com.example.expoapp_v2.common.datasource.remote

import com.example.expoapp_v2.common.service.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

open class BaseRemoteDataSource {

    protected fun <T> getResultFromApi(
        apiCall: suspend () -> Response<T>
    ): Flow<ApiResult<T>> {
        return flow {
            try {
                val apiCallResult = apiCall.invoke()
                if (apiCallResult.isSuccessful) {
                    val body = apiCallResult.body()
                    if (body != null)
                        emit(ApiResult.Success(body))
                    else
                        emit(ApiResult.Error(Exception("Empty network response body")))
                } else {
                    emit(ApiResult.Error(Exception("Unsuccessful api call")))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
        }
    }
}