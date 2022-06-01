package com.example.rickandmorty.character.data

import com.example.rickandmorty.character.api.HttpException
import com.example.rickandmorty.character.api.IoException
import com.example.rickandmorty.character.api.RickAndMortyApi
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.CharacterRepository
import com.example.rickandmorty.character.domain.EpisodeResponseItem
import com.example.rickandmorty.character.toDomain
import com.example.rickandmorty.global.Result
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getAllCharacters(page: Int): Result<List<Character>, Throwable> {
        return try {
            Result.Success(api.getCharacters(page).toDomain().characters)
        } catch (e: IOException) {
            Result.Exception(IoException)
        } catch (e: retrofit2.HttpException) {
            Result.Exception(HttpException)
        }
    }

    override suspend fun getEpisodes(episodes: List<String>): Result<List<EpisodeResponseItem>, Throwable> {
        return try {
            Result.Success(api.getEpisodes(EpisodeToPath(episodes)).map { it.toDomain() })
        } catch (e: IOException) {
            Result.Exception(IoException)
        } catch (e: retrofit2.HttpException) {
            Result.Exception(HttpException)
        }
    }
}