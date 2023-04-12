package com.example.myapplication

interface AnimalInteractor {
    suspend fun init(): AnimalResult
    suspend fun factAboutAnimal(animalList: String) : AnimalResult
}