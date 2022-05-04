package com.example.rickandmorty.character.domain

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke() = characterRepository.getAllCharacters()

}