package com.example.rickandmorty.character

import com.example.rickandmorty.character.api.models.CharacterDto
import com.example.rickandmorty.character.api.models.EpisodeResponseItemDto
import com.example.rickandmorty.character.api.models.InfoDto
import com.example.rickandmorty.character.api.models.LocationDto
import com.example.rickandmorty.character.api.models.OriginDto
import com.example.rickandmorty.character.api.models.ResponseCharacterDto
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.EpisodeResponseItem
import com.example.rickandmorty.character.domain.Info
import com.example.rickandmorty.character.domain.Location
import com.example.rickandmorty.character.domain.Origin
import com.example.rickandmorty.character.domain.ResponseCharacter

fun CharacterDto.toDomain(): Character {
    return Character(
        created = created,
        episode = episode,
        gender = gender,
        id = id,
        image = image,
        location = location.toDomain(),
        name = name,
        origin = origin.toDomain(),
        species = species,
        status = status,
        type = type,
        url = url
    )
}

fun LocationDto.toDomain(): Location {
    return Location(name = name, url = url)
}

fun OriginDto.toDomain(): Origin {
    return Origin(name = name, url = url)
}

fun InfoDto.toDomain(): Info {
    return Info(count = count, next = next, pages = 0, prev = prev)
}

fun ResponseCharacterDto.toDomain(): ResponseCharacter {
    return ResponseCharacter(
        info = info.toDomain(), characters = characters.map { it.toDomain() }
    )
}

fun EpisodeResponseItemDto.toDomain(): EpisodeResponseItem {
    return EpisodeResponseItem(
        airDate = airDate,
        characters = characters,
        created = created,
        episode = episode,
        id = id,
        name = name,
        url = url
    )
}