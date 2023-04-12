package com.example.myapplication.animal.domain

sealed class AnimalResult {


    interface Mapper<T>{
        fun map(list: List<AnimalFact>, errorMessage: String): T
    }

    abstract fun <T> map(mapper: Mapper<T>): T

    class Success(private val list: List<AnimalFact> = emptyList()) : AnimalResult() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(list, "")
        }
    }

    class Failure(private val message: String): AnimalResult() {
        override fun <T> map(mapper: Mapper<T>): T {
            return mapper.map(emptyList(), message)
        }
    }
}