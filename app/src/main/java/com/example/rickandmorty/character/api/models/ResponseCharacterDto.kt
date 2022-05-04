package com.example.rickandmorty.character.api.models

data class ResponseCharacterDto(
    val info: InfoDto,
    val characters: List<CharacterDto>
)