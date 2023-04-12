package com.example.myapplication.animal.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.animal.domain.AnimalInteractor
import com.example.myapplication.animal.domain.AnimalResult
import com.example.myapplication.R
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val manageResources: ManageResources,
    private val interactor: AnimalInteractor,
    private val communication: AnimalCommunication,
    private val animalResultMapper: AnimalResult.Mapper<Unit>

) : ObserveAnimals, FetchAnimal, ViewModel() {

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
           viewModelScope.launch {
                val result = interactor.init()
                communication.showProgress(false)
                result.map(animalResultMapper)
            }
        }
    }

    override fun fetchFact() {
        TODO("Not yet implemented")
    }

    override fun fetchAnimalData(animal: String) {
        if (animal.isEmpty()){
            communication.showState(UiState.Error(manageResources.string(R.string.empty)))
        } else
        {
            communication.showProgress(true)
            viewModelScope.launch {
                val result = interactor.factAboutAnimal(animal) //должен быть лист
                communication.showProgress(false)
                result.map(animalResultMapper)
            }
        }
    }
}