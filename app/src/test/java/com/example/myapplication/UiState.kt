package com.example.myapplication

sealed class UiState {
    object Authorization : UiState()

    object NotAuthorization : UiState()
}