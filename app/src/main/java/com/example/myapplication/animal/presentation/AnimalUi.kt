package com.example.myapplication.animal.presentation

import android.widget.TextView

data class AnimalUi(private val name: String, private val fact: String) {
    fun map(head: TextView, subTitle: TextView){
        head.text = name
        subTitle.text = fact
    }

}