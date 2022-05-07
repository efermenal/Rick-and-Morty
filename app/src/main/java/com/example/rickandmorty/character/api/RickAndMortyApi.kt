package com.example.rickandmorty.character.api

import com.example.rickandmorty.character.api.models.ResponseCharacterDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("/api/character")
    suspend fun getCharacters(@Query("page") page: Int): ResponseCharacterDto

}

object Url {
    const val API = "https://rickandmortyapi.com"
}