package com.example.myapplication.presentation

import com.example.myapplication.AnimalFact

class AnimalUiMapper: AnimalFact.Mapper<AnimalUi> {
    override fun map(animalName: String, fact: String): AnimalUi {
        return AnimalUi(animalName, fact)
    }
}