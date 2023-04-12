package com.example.myapplication.animal.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.myapplication.core.Communication

interface AnimalCommunication : ObserveAnimals {

    fun showProgress(show: Boolean)
    fun showState(uiState: UiState)
    fun showList(list: List<AnimalUi>)

    class Base(
        private val progressCommunication: ProgressCommunication,
        private val stateCommunication: AnimalsStateCommunication,
        private val listCommunication: AnimalsListCommunication
    ) : AnimalCommunication {
        override fun showProgress(show: Boolean) {
            progressCommunication.map(show)
        }

        override fun showState(uiState: UiState) {
            stateCommunication.map(uiState)
        }

        override fun showList(list: List<AnimalUi>) {
            listCommunication.map(list)
        }

        override fun observeProgress(owner: LifecycleOwner, observe: Observer<Boolean>) {
            progressCommunication.observe(owner, observe)
        }

        override fun observeState(owner: LifecycleOwner, observe: Observer<UiState>) {
            stateCommunication.observe(owner, observe)
        }

        override fun observeList(owner: LifecycleOwner, observe: Observer<List<AnimalUi>>) {
           listCommunication.observe(owner, observe)
        }
    }
}

interface ProgressCommunication : Communication.Mutable<Boolean> {
    class Base : Communication.Post<Boolean>(), ProgressCommunication
}

interface AnimalsStateCommunication : Communication.Mutable<UiState> {
    class Base : Communication.Post<UiState>(), AnimalsStateCommunication
}

interface AnimalsListCommunication : Communication.Mutable<List<AnimalUi>> {
    class Base : Communication.Post<List<AnimalUi>>(), AnimalsListCommunication
}


