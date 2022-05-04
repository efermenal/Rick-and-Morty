package com.example.rickandmorty.character.api.models

data class InfoDto(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String?,
)