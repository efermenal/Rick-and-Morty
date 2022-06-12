package com.example.rickandmorty.character.domain

data class EpisodeResponseItem(
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    /**
     * API REST URL
     *
     * It's null if comes from GraphQL
     */
    val url: String? = null
)