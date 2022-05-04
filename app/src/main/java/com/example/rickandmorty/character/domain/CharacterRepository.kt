package com.example.rickandmorty.character.domain

import com.example.rickandmorty.global.Result

interface CharacterRepository {
    suspend fun getAllCharacters(): Result<List<Character>, Throwable>
}
