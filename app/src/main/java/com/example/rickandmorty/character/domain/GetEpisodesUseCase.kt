package com.example.rickandmorty.character.domain

class GetEpisodesUseCase(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(episodes: List<String>) = characterRepository.getEpisodes(episodes)
}