package com.example.expoapp_v2.characterdetail.domain.mapper

import com.example.expoapp_v2.characterdetail.domain.model.DetailedCharacter
import com.example.expoapp_v2.characterdetail.service.model.CharacterResult


fun CharacterResult.toDomainModel() = DetailedCharacter(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image,
    episode = episode
)