package com.example.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.myapplication.AnimalFact
import com.example.myapplication.AnimalInteractor
import com.example.myapplication.AnimalResult
import com.example.myapplication.UiState

class AnimalViewModel(
    private val interactor: AnimalInteractor,
    private val communication: AnimalCommunication,
    private val mapper: AnimalFact.Mapper<AnimalUi> = AnimalUiMapper()

) : ObserveAnimals, FetchAnimal {

    override fun observeProgress(owner: LifecycleOwner, observe: Observer<Boolean>) {
        communication.observeProgress(owner, observe)
    }

    override fun observeState(owner: LifecycleOwner, observe: Observer<UiState>) {
        communication.observeState(owner, observe)
    }

    override fun observeList(owner: LifecycleOwner, observe: Observer<List<AnimalUi>>) {
        communication.observeList(owner, observe)
    }

    override fun init(isFirstRun: Boolean) {
        if (isFirstRun) {
            communication.showProgress(true)
            viewModelScoupe.launch {
                val result = interactor.init()
                result.map(object : AnimalResult.Mapper<Unit> {
                    override fun map(list: List<AnimalFact>, errorMessage: String) {
                        if (errorMessage.isEmpty()) {
                            communication.showState(UiState.Success(list.map {
                                it.map(mapper)
                            }))
                        } else{
                            communication.showState(UiState.Error(errorMessage))
                        }
                    }

                })
            }
        }
    }

    override fun fetchFact() {
        TODO("Not yet implemented")
    }

    override fun fetchAnimalData(animal: String) {
        TODO("Not yet implemented")
    }


}