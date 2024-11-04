package com.example.myapplication

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var imagesArr = arrayOf((0..7).random(),(0..7).random(),(0..7).random(),(0..7).random()
        ,(0..7).random(),(0..7).random(),(0..7).random(),(0..7).random())
    var titleArr = arrayOf((0..7).random(),(0..7).random(),(0..7).random(),(0..7).random()
        ,(0..7).random(),(0..7).random(),(0..7).random(),(0..7).random())
    var detailArr = arrayOf((0..7).random(),(0..7).random(),(0..7).random(),(0..7).random()
        ,(0..7).random(),(0..7).random(),(0..7).random(),(0..7).random())
}