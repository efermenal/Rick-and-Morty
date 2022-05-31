package com.example.rickandmorty.character.data

data class EpisodeToPath(val episodes: List<String>) {
    override fun toString(): String {
        if (episodes.isEmpty()) return ""

        return episodes.joinToString(separator = ",", prefix = "[", postfix = "]") {
            it.substringAfterLast("/")
        }
    }
}