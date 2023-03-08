package com.example.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.animal.presentation.AnimalFragment

class MainActivity : AppCompatActivity(), ShowFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            NavigationStrategy.Replace(AnimalFragment()).navigate(supportFragmentManager, R.id.container)
        }
    }

    override fun show(fragment: Fragment) {
        NavigationStrategy.Add(fragment).navigate(supportFragmentManager, R.id.container)
    }
}

interface ShowFragment{
    fun show(fragment: Fragment)

    class Empty(): ShowFragment {
        override fun show(fragment: Fragment) {
            return Unit
        }
    }
}