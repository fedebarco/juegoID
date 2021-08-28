package com.example.juegoid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.juegoid.databinding.ActivityMainBinding
import com.example.juegoid.rooma.Usuarios
import com.example.juegoid.rooma.UsuariosDb
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var local: UsuariosDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = Firebase.database.reference

        binding.nuevo.setOnClickListener {
            val bol=true
            val nombrecito=binding.nombre.text.toString()
            if(nombrecito!="") {
                val intenta = Intent(this, SalaEspera::class.java).apply {
                    lifecycleScope.launch {
                        var codigo=""
                        val source= mutableListOf('A','B','C','D','E','F','G','H','I','J')
                        for (i in 1..4){
                            codigo+=source.random()
                        }
                        val usu1=Usuarios(0,nombrecito,codigo,true,80)
                        val lusu= mutableListOf(usu1)
                        database.child("juegos").child(codigo+"n").setValue(codigo)
                        local.usuariosDao().inserte(lusu)
                        local.usuariosDao().update(lusu[0])
                    }
                    putExtra("bor", bol)
                }
                startActivity(intenta)
            }else{
                binding.error.text="llena tu nombre"
            }
        }
        binding.unirse.setOnClickListener {
            val bol=false
            val nombrecito=binding.nombre.text.toString()
            val codigo = binding.usuario2.text.toString()
            var post: String
            if(nombrecito!="") {
                database.child("juegos").child(codigo+"n").get().addOnSuccessListener {
                    post=it.value.toString()
                    if (post==codigo) {
                        val intenta = Intent(this, SalaEspera::class.java).apply {
                            lifecycleScope.launch {
                                var usu1=Usuarios(0,nombrecito,codigo,true,80)
                                var lusu= mutableListOf(usu1)
                                database.child("juegos").child(codigo+"n").setValue(codigo)
                                local.usuariosDao().inserte(lusu)
                                local.usuariosDao().update(lusu[0])
                            }
                            putExtra("bor", bol)
                        }
                        startActivity(intenta)
                    }else{
                        binding.error.text="introduce un codigo valido"
                    }

                }.addOnFailureListener{
                    post="noooooo"
                }

            }else{
                binding.error.text="llena tu nombre"
            }
        }
    }

}