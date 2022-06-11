package com.example.rickandmorty.character.domain

data class Location(
    val name: String,
    /**
     * API REST URL
     *
     * It's null if comes from GraphQL
     */
    val url: String? = null
)