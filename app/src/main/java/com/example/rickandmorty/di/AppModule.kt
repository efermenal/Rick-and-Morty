package com.example.rickandmorty.di

import com.example.rickandmorty.character.domain.CharacterRepository
import com.example.rickandmorty.character.domain.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(characterRepository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }


}