package com.example.myapplication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.myapplication.presentation.Mapper

interface Communication {

    interface Observe<T>{
        fun observe(owner: LifecycleOwner, observe: Observer<T>)
    }

    interface Mutate<T> : Mapper.Unit<T>

    interface Mutable<T>: Observe<T>, Mutate<T>

    abstract class Abstract<T>(protected val liveData: MutableLiveData<T> = MutableLiveData()) : Mutable<T>{
        override fun observe(owner: LifecycleOwner, observe: Observer<T>) {
            liveData.observe(owner, observe)
        }
    }

    abstract class UI<T>(liveData: MutableLiveData<T> = MutableLiveData()): Abstract<T>(liveData){
        override fun map(source: T) {
            liveData.value = source
        }
    }

    abstract class Post<T>(liveData: MutableLiveData<T> = MutableLiveData()): Abstract<T>(liveData){
        override fun map(source: T) {
            return liveData.postValue(source)
        }

    }







}