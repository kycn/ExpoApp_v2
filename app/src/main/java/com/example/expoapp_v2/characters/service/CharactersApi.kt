package com.example.expoapp_v2.characters.service

import com.example.expoapp_v2.characters.service.model.CharactersResult
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(): Response<CharactersResult>
}