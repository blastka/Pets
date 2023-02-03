package com.example.myapplication

import com.example.myapplication.presentation.AnimalUi

sealed class UiState {

    interface Mapper<T>{
        fun map(list: List<AnimalUi>, message: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    class Error(private val message: String) : UiState() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(emptyList(), message)
        }
    }

    class Success(private val list: List<AnimalUi> = emptyList()) : UiState() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(list, "")
        }
    }
}