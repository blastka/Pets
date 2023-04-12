package com.example.myapplication.animal.presentation

import android.content.Context
import androidx.annotation.StringRes

interface ManageResources {

    fun string(@StringRes id: Int): String

    class Base(private val context: Context): ManageResources {
        override fun string(id: Int): String {
            return context.getString(id)
        }

    }

}