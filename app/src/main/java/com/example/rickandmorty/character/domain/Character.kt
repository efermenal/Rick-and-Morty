package com.example.rickandmorty.character.domain

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    /**
     * API REST URL
     *
     * It's null if comes from GraphQL
     */
    val url: String? = null
)