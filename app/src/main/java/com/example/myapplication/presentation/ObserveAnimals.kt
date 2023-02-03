package com.example.myapplication.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.myapplication.UiState

interface ObserveAnimals{
    fun observeProgress(owner: LifecycleOwner, observe: Observer<Boolean>)
    fun observeState(owner: LifecycleOwner, observe: Observer<UiState>)
    fun observeList(owner: LifecycleOwner, observe: Observer<List<AnimalUi>>)
}