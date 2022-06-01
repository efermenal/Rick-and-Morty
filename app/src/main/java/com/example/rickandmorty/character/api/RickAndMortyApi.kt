package com.example.rickandmorty.character.api

import com.example.rickandmorty.character.api.models.EpisodeResponseItemDto
import com.example.rickandmorty.character.api.models.ResponseCharacterDto
import com.example.rickandmorty.character.data.EpisodeToPath
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(@Query("page") page: Int): ResponseCharacterDto

    @GET("/api/episode/{episodes}")
    suspend fun getEpisodes(@Path("episodes") episode: EpisodeToPath): List<EpisodeResponseItemDto>

}

object Url {
    const val API = "https://rickandmortyapi.com"
}