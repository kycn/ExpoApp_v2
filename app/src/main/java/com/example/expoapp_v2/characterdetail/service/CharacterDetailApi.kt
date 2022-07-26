package com.example.expoapp_v2.characterdetail.service

import com.example.expoapp_v2.characterdetail.service.model.CharacterResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDetailApi {
    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<CharacterResult>
}