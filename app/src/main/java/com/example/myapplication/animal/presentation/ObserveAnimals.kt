package com.example.myapplication.animal.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ObserveAnimals{
    fun observeProgress(owner: LifecycleOwner, observe: Observer<Boolean>)
    fun observeState(owner: LifecycleOwner, observe: Observer<UiState>)
    fun observeList(owner: LifecycleOwner, observe: Observer<List<AnimalUi>>)
}