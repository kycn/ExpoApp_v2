package com.example.expoapp_v2.characterdetail.domain.mapper

import com.example.expoapp_v2.characterdetail.domain.model.DetailedEpisode
import com.example.expoapp_v2.characterdetail.service.model.EpisodeResult


fun EpisodeResult.toDomainModel() = DetailedEpisode(
    name = name,
    airData = air_date,
    episodeName = episode,
)