package com.example.rickandmorty.character.api.models

import com.squareup.moshi.Json

data class ResponseCharacterDto(
    @Json(name = "info") val info: InfoDto,
    @Json(name = "results") val characters: List<CharacterDto>
)