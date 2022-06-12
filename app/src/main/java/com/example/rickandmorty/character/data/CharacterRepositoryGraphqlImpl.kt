package com.example.rickandmorty.character.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.rickandmorty.CharactersQuery
import com.example.rickandmorty.EpisodesQuery
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
            return Result.Success(characterToDomain(characterDto))
        } catch (e: IOException) {
            Result.Exception(IoException)
        } catch (e: ApolloException) {
            Result.Exception(HttpException)
        } catch (e: NullPointerException) {
            Result.Exception(ParsingException)
        }

    }

    override suspend fun getEpisodes(episodes: List<String>): Result<List<EpisodeResponseItem>, Throwable> {
        return try {
            val response = api.query(EpisodesQuery(ids = episodes)).execute()
            val episodeDto = response.data?.episodesByIds
            return Result.Success(episodeToDomain(episodeDto))
        } catch (e: IOException) {
            Result.Exception(IoException)
        } catch (e: ApolloException) {
            Result.Exception(HttpException)
        } catch (e: NullPointerException) {
            Result.Exception(ParsingException)
        }

    }

    private fun characterToDomain(characterDto: List<CharactersQuery.Result?>?): List<Character> {
        // TODO: improve this object creation
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

    private fun episodeToDomain(episodeDto: List<EpisodesQuery.EpisodesById?>?): List<EpisodeResponseItem> {
        // TODO: improve this parsing
        episodeDto?.let {
            return episodeDto.map { it ->
                EpisodeResponseItem(
                    airDate = it?.air_date!!,
                    characters = it.characters.map { it?.id!! },
                    created = it.created!!,
                    episode = it.episode!!,
                    id = it.id!!.toInt(),
                    name = it.name!!,
                )
            }
        }
        throw NullPointerException()
    }

}