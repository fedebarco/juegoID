package com.example.juegoid

import android.app.Application
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {
    var codigo="ABCD"
    fun cambiacodigo(c:String){
        codigo=c
    }




}