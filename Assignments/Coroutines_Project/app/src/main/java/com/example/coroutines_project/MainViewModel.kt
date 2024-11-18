package com.example.coroutines_project

import androidx.lifecycle.ViewModel

class mainViewModel : ViewModel() {
    val names = mutableListOf<String>()


    fun addString(name:String){
        names.add(name)
    }
}