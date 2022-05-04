package com.example.rickandmorty.character.data

import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.CharacterRepository
import com.example.rickandmorty.global.Result

class CharacterRepositoryImpl : CharacterRepository {
    override suspend fun getAllCharacters(): Result<List<Character>, Throwable> {
        TODO("Not yet implemented")
    }
}