package com.example.rickandmorty.character.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.rickandmorty.CharactersQuery
import com.example.rickandmorty.character.api.HttpException
import com.example.rickandmorty.character.api.IoException
import com.example.rickandmorty.character.api.ParsingException
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.CharacterRepository
import com.example.rickandmorty.character.domain.EpisodeResponseItem
import com.example.rickandmorty.character.domain.Location
import com.example.rickandmorty.character.domain.Origin
import com.example.rickandmorty.global.Result
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryGraphqlImpl @Inject constructor(
    private val api: ApolloClient
) : CharacterRepository {
    override suspend fun getAllCharacters(page: Int): Result<List<Character>, Throwable> {
        return try {
            val response = api.query(CharactersQuery(page = page)).execute()
            val characterDto = response.data?.characters?.results
            return Result.Success(toDomain(characterDto))
        } catch (iOException: IOException) {
            Result.Exception(IoException)
        } catch (apolloException: ApolloException) {
            Result.Exception(HttpException)
        } catch (nullPointerException: NullPointerException) {
            Result.Exception(ParsingException)
        }

    }

    override suspend fun getEpisodes(episodes: List<String>): Result<List<EpisodeResponseItem>, Throwable> {
        TODO("Not yet implemented")
    }


    private fun toDomain(characterDto: List<CharactersQuery.Result?>?): List<Character> {
        characterDto?.let { dtoList ->
            return dtoList.map {
                Character(
                    created = it?.created!!,
                    episode = it?.episode.map { episode -> episode?.id!! },
                    gender = it.gender!!,
                    id = it.id!!.toInt(),
                    image = it.image!!,
                    location = Location(name = it.location?.name!!),
                    name = it.name!!,
                    origin = Origin(name = ""),
                    species = it.species!!,
                    status = it.status!!,
                    type = it.type!!,
                )
            }
        }
        throw NullPointerException()
    }


}