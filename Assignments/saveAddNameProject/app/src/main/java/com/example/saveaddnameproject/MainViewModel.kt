package com.example.saveaddnameproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel(){
    val names = MutableLiveData<String>()

    fun addName(newName : String){
        if(names.value == null){
            names.value = newName
        }
        else {
            names.value = names.value + "\n" + newName
        }
    }
}