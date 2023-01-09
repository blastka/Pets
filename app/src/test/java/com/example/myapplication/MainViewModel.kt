package com.example.myapplication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

class MainViewModel(
    private val repository: MainRepository,
    private val communication: MainCommunication.Mutable
): MainCommunication.Observe {
    fun init(isFirstRun: Boolean){
        if (isFirstRun){
            val authorization = repository.authorization()
            communication.put(
                if (authorization){
                UiState.Authorization
            } else
                UiState.NotAuthorization
            )
        }
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<UiState>) {
        communication.observe(owner, observer)
    }
}