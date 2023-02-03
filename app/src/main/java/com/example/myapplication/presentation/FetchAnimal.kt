package com.example.myapplication.presentation

interface FetchAnimal {
    fun init(isFirstRun: Boolean)
    fun fetchFact()
    fun fetchAnimalData(animal: String)
}