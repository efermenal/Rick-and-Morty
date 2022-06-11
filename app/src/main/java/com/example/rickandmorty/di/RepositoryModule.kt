package com.example.rickandmorty.di

import com.example.rickandmorty.character.data.CharacterRepositoryGraphqlImpl
import com.example.rickandmorty.character.data.CharacterRepositoryImpl
import com.example.rickandmorty.character.domain.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    @Named("rest")
    abstract fun bindCharacterApiRepository(characterRepositoryImpl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    @Singleton
    @Named("graph")
    abstract fun bindCharacterGraphRepository(characterRepositoryGraphqlImpl: CharacterRepositoryGraphqlImpl): CharacterRepository
}