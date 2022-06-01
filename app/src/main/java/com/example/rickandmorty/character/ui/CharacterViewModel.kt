package com.example.rickandmorty.character.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.EpisodeResponseItem
import com.example.rickandmorty.character.domain.GetCharactersUseCase
import com.example.rickandmorty.character.domain.GetEpisodesUseCase
import com.example.rickandmorty.global.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getEpisodesUseCase: GetEpisodesUseCase,
) : ViewModel() {

    private val viewStateFlow: MutableStateFlow<ViewState> = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = viewStateFlow

    private val commandChannel =
        Channel<Command>(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val commands = commandChannel.receiveAsFlow()

    sealed class Command {
        data class NavigateToEpisode(val episodes: List<EpisodeResponseItem>) : Command()
        object ShowErrorMessage : Command()
    }

    data class ViewState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false,
        val page: Int = 1,
    )

    private fun currentViewState(): ViewState = viewState.value

    fun getCharacters() {
        viewModelScope.launch {
            viewStateFlow.emit(currentViewState().copy(isLoading = true))
            runCatching {
                getCharactersUseCase(currentViewState().page)
            }.onSuccess {
                when (it) {
                    is Result.Error -> {
                        stopLoadAndEmitError()
                    }
                    is Result.Exception -> {
                        stopLoadAndEmitError()
                    }
                    is Result.Success -> {
                        viewStateFlow.emit(
                            currentViewState().copy(
                                characters = currentViewState().characters + it.data,
                                isLoading = false,
                                page = currentViewState().page + 1
                            )
                        )
                    }
                }
            }.onFailure {
                stopLoadAndEmitError()
            }
        }
    }

    private suspend fun stopLoadAndEmitError() {
        viewStateFlow.emit(currentViewState().copy(isLoading = false))
        commandChannel.send(Command.ShowErrorMessage)
    }

    fun getEpisodes(episodeList: List<String>) {
        viewModelScope.launch {
            runCatching {
                getEpisodesUseCase(episodeList)
            }.onSuccess {
                when (it) {
                    is Result.Error -> {
                        commandChannel.send(Command.ShowErrorMessage)
                    }
                    is Result.Exception -> {
                        commandChannel.send(Command.ShowErrorMessage)
                    }
                    is Result.Success -> {
                        commandChannel.send(Command.NavigateToEpisode(it.data))
                    }
                }
            }.onFailure {
                commandChannel.send(Command.ShowErrorMessage)
            }
        }
    }
}