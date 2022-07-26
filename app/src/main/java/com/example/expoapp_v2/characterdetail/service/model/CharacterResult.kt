package com.example.expoapp_v2.characterdetail.service.model

data class CharacterResult(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val episode: List<String>
)