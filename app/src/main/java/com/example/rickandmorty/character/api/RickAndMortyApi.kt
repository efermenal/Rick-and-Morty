package com.example.rickandmorty.character.api

import com.example.rickandmorty.character.api.models.ResponseCharacterDto
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(): ResponseCharacterDto

}

object Url {
    const val API = "https://rickandmortyapi.com"
}