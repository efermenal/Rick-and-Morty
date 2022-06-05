package com.example.rickandmorty.character.ui

import com.example.rickandmorty.character.domain.EpisodeResponseItem
import java.io.Serializable

data class EpisodeArguments(
    val episodes: List<EpisodeResponseItem>,
) : Serializable
