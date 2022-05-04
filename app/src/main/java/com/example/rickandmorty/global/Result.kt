package com.example.rickandmorty.global

sealed class Result<out S, out F> {
    data class Success<S>(val data: S) : Result<S, Nothing>()
    data class Error<F>(val error: F) : Result<Nothing, F>()
    data class Exception(val cause: Cause) : Result<Nothing, Nothing>() {
        interface Cause
    }
}