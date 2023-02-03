package com.example.myapplication.presentation

import com.example.myapplication.AnimalFact
import com.example.myapplication.AnimalResult
import com.example.myapplication.UiState

class AnimalResultMapper(
    private val communication: AnimalCommunication,
    private val mapper: AnimalFact.Mapper<AnimalUi>,
) : AnimalResult.Mapper<Unit> {
    override fun map(list: List<AnimalFact>, errorMessage: String) {
        communication.showState(
            if (errorMessage.isEmpty()) {
                val numberList = list.map {
                    it.map(mapper)
                }
                if (errorMessage.isNotEmpty())
                    communication.showList(numberList)
                UiState.Success()
            } else
                UiState.Error(errorMessage)
        )
    }
}