package com.example.myapplication.animal.domain

interface AnimalInteractor {
    suspend fun init(): AnimalResult
    suspend fun factAboutAnimal(animalList: String) : AnimalResult
}