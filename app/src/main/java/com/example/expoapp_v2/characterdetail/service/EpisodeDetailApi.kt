package com.example.expoapp_v2.characterdetail.service

import com.example.expoapp_v2.characterdetail.service.model.EpisodeResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeDetailApi {
    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Response<EpisodeResult>
}