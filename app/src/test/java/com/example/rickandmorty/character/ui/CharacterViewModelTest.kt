package com.example.rickandmorty.character.ui

import com.example.rickandmorty.CoroutineTestRule
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.CharacterRepository
import com.example.rickandmorty.character.domain.GetCharactersUseCase
import com.example.rickandmorty.character.domain.Location
import com.example.rickandmorty.character.domain.Origin
import com.example.rickandmorty.character.ui.CharacterViewModel.Command
import com.example.rickandmorty.global.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CharacterViewModelTest {

    @get:Rule
    var coroutineRule = CoroutineTestRule()

    private val characterRepository: CharacterRepository = mock()
    private val getCharactersUseCase = GetCharactersUseCase(characterRepository)
    private val viewModel: CharacterViewModel by lazy {
        CharacterViewModel(getCharactersUseCase)
    }

    @Test
    fun whenCallToApiSuccessReturnCharacters() = runTest {
        whenever(getCharactersUseCase(1)).thenAnswer {
            Result.Success(
                getDefaultDummyCharacters()
            )
        }

        viewModel.getCharacters()

        assertEquals(getDefaultDummyCharacters(), viewModel.viewState.value.characters)
    }

    @Test
    fun whenCallToApiFailsReturnError() = runTest {
        whenever(getCharactersUseCase(1)).thenThrow(RuntimeException())

        viewModel.getCharacters()

        assertEquals(Command.ShowErrorMessage, viewModel.commands.first())
    }


    private fun getDefaultDummyCharacters(): List<Character> {
        return listOf(
            Character(
                created = "Rick",
                episode = listOf(),
                gender = "",
                id = 0,
                image = "",
                location = Location(name = "", url = ""),
                name = "",
                origin = Origin(name = "", url = ""),
                species = "",
                status = "",
                type = "",
                url = ""
            ),
            Character(
                created = "Morty",
                episode = listOf(),
                gender = "",
                id = 1,
                image = "",
                location = Location(name = "", url = ""),
                name = "",
                origin = Origin(name = "", url = ""),
                species = "",
                status = "",
                type = "",
                url = ""
            )
        )
    }

}