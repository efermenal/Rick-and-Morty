package com.example.rickandmorty.character.domain

class GetCharactersUseCase(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(page: Int) = characterRepository.getAllCharacters(page)

}