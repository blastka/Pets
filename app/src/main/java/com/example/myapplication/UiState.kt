package com.example.myapplication

import com.example.myapplication.presentation.AnimalUi

sealed class UiState {

    interface Mapper<T>{
        fun map(message: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    class Error(private val message: String) : UiState() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(message)
        }
    }

    class Success() : UiState() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map("")
        }
    }
}