package com.example.expoapp_v2.characters.domain.model.mapper

import com.example.expoapp_v2.characters.domain.model.CharacterItem
import com.example.expoapp_v2.characters.service.model.RemoteCharacterItem

fun RemoteCharacterItem.toDomainModel() = CharacterItem(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image
)