package com.example.myapplication.animal.domain

data class AnimalFact(private val animalName: String, private val fact: String) {

    interface Mapper<T> {
        fun map(animalName: String, fact: String): T
    }
    fun <T> map(mapper: Mapper<T>): T = mapper.map(animalName, fact)
}