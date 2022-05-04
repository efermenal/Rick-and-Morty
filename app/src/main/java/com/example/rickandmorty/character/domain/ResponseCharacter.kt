package com.example.rickandmorty.character.domain

data class ResponseCharacter(
    val info: Info,
    val characters: List<Character>
)