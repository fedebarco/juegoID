package com.example.juegoid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.juegoid.databinding.ActivityFinalBinding
import com.example.juegoid.databinding.ActivityJugarBinding

class Final : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}