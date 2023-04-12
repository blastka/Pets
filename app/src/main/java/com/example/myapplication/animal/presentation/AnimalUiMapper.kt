package com.example.myapplication.animal.presentation

import com.example.myapplication.animal.domain.AnimalFact

class AnimalUiMapper: AnimalFact.Mapper<AnimalUi> {
    override fun map(animalName: String, fact: String): AnimalUi {
        return AnimalUi(animalName, fact)
    }
}