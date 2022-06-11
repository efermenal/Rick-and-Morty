package com.example.rickandmorty.di

import com.example.rickandmorty.character.domain.CharacterRepository
import com.example.rickandmorty.character.domain.GetCharactersUseCase
import com.example.rickandmorty.character.domain.GetEpisodesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideGetCharactersUseCase(@Named("graph") characterRepository: CharacterRepository): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository)
    }

    @Provides
    @Singleton
    fun provideGetEpisodeUseCase(@Named("graph") characterRepository: CharacterRepository): GetEpisodesUseCase {
        return GetEpisodesUseCase(characterRepository)
    }

}