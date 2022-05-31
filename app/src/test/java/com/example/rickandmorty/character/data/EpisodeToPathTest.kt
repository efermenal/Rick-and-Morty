package com.example.rickandmorty.character.data

import org.junit.Assert.assertEquals
import org.junit.Test

class EpisodeToPathTest {

    @Test
    fun whenEpisodeListIsEmptyReturnsEmpty() {
        val result = EpisodeToPath(emptyList())
        assertEquals("", result.toString())
    }

    @Test
    fun whenMultipleEpisodeListIsValidReturnsFormattedEpisodes() {
        val result = EpisodeToPath(multipleListOfEpisodes())
        assertEquals("[1,2,3]", result.toString())
    }

    @Test
    fun whenSingleEpisodeListIsValidReturnsFormattedEpisode() {
        val result = EpisodeToPath(listOf("https://rickandmortyapi.com/api/episode/1"))
        assertEquals("[1]", result.toString())
    }

    private fun multipleListOfEpisodes(): List<String> {
        return listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3",
        )
    }

}