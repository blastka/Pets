package com.example.myapplication

import com.example.myapplication.presentation.AnimalUi
import com.example.myapplication.presentation.Mapper

data class AnimalFact(private val animalName: String, private val fact: String) {

    interface Mapper<T> {
        fun map(animalName: String, fact: String): T
    }
    fun <T> map(mapper: Mapper<T>): T = mapper.map(animalName, fact)
}