package com.example.expoapp_v2.characters.service.model

data class CharactersResult(
    val results: List<CharacterItem>
)

data class CharacterItem(
    val id : Int,
    val name: String,
    val status: String,
    val species : String,
    val gender: String,
    val image: String
)