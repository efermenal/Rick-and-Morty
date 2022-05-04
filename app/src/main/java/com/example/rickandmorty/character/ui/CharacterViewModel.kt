package com.example.rickandmorty.character.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.character.domain.Character
import com.example.rickandmorty.character.domain.GetCharactersUseCase
import com.example.rickandmorty.global.Result
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val viewStateFlow: MutableStateFlow<ViewState> = MutableStateFlow(ViewState())
    val viewState: StateFlow<ViewState> = viewStateFlow

    private val commandChannel =
        Channel<Command>(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val commands = commandChannel.receiveAsFlow()

    sealed class Command {
        object ShowErrorMessage : Command()
    }

    data class ViewState(
        val characters: List<Character> = emptyList(),
        val isLoading: Boolean = false,
    )
    
    private fun currentViewState(): ViewState = viewState.value

    fun getCharacters() {
        viewModelScope.launch {
            viewStateFlow.emit(currentViewState().copy(isLoading = true))
            runCatching {
                getCharactersUseCase()
            }.onSuccess {
                when(it) {
                    is Result.Error -> {
                        stopLoadAndEmitError()
                    }
                    is Result.Exception -> {
                        stopLoadAndEmitError()
                    }
                    is Result.Success -> {
                        viewStateFlow.emit(
                            currentViewState().copy(
                                characters = it.data,
                                isLoading = false
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
}