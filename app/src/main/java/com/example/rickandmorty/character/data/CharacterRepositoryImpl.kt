package com.example.rickandmorty.character.data

import com.example.rickandmorty.character.api.HttpException
import com.example.rickandmorty.character.api.IoException
import com.example.rickandmorty.character.api.RickAndMortyApi
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.CharacterRepository
import com.example.rickandmorty.character.toDomain
import com.example.rickandmorty.global.Result
import java.io.IOException

class CharacterRepositoryImpl(private val api: RickAndMortyApi) : CharacterRepository {
    override suspend fun getAllCharacters(): Result<List<Character>, Throwable> {
        return try {
            Result.Success(api.getCharacters().toDomain().characters)
        } catch (e: IOException) {
            Result.Exception(IoException)
        } catch (e: retrofit2.HttpException) {
            Result.Exception(HttpException)
        }
    }
}