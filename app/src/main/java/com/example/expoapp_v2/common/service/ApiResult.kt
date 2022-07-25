package com.example.expoapp_v2.common.service

sealed class ApiResult<out R> {

    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}