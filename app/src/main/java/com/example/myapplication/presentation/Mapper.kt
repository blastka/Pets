package com.example.myapplication.presentation

import com.example.myapplication.UiState

interface Mapper<R, S> {
    fun map(source: S): R

    interface Unit<S> : Mapper<kotlin.Unit, S>
}