package com.example.myapplication.animal.presentation

interface FetchAnimal {
    fun init(isFirstRun: Boolean)
    fun fetchFact()
    fun fetchAnimalData(animal: String)
}