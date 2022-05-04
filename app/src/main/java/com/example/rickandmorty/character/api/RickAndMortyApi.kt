package com.example.rickandmorty.character.api

import com.example.rickandmorty.character.api.models.ResponseCharacterDto
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("/api/character")
    fun getCharacters(): ResponseCharacterDto

}

object url {
    const val API = "https://rickandmortyapi.com"
}