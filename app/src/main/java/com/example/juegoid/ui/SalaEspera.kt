package com.example.juegoid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.juegoid.databinding.ActivitySalaEsperaBinding
import com.example.juegoid.ui.csalaespera.ConfiguracionFragment
import com.example.juegoid.ui.csalaespera.EsperarFragment
import com.example.juegoid.ui.csalaespera.VMSalaE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalaEspera : AppCompatActivity() {
    var context=this
    lateinit var binding:ActivitySalaEsperaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySalaEsperaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model: VMSalaE by viewModels()
        //recupera las variables
        model.principal.postValue(intent.getBooleanExtra("bor",true))



        binding.configurar.setOnClickListener {
            cambiaFragment()
        }



        //objetos llamada
        model.iniciaOtraA.observe(this,{
            if(it) {
                val intentui = Intent(context, Jugar::class.java).apply {
                    putExtra("cfin", model.codigoJuego.value!!)
                    putExtra("nname", model.nombreU.value!!)
                    putExtra("esTrue", model.principal.value!!)
                }
                startActivity(intentui)
            }
        })



    }
    override fun onBackPressed() {

        when(supportFragmentManager.fragments[0].javaClass.simpleName){
            "ConfiguracionFragment" -> cambiaFragment()
            else -> supportFragmentManager.popBackStack()
        }
    }

    fun cambiaFragment(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        when(supportFragmentManager.fragments[0].javaClass.simpleName){
            "ConfiguracionFragment"-> {
                val fragment = EsperarFragment()
                fragmentTransaction.replace(binding.viewer.id, fragment)
                fragmentTransaction.commit()
            }
            "EsperarFragment"-> {
                val fragment = ConfiguracionFragment()
                fragmentTransaction.replace(binding.viewer.id, fragment)
                fragmentTransaction.commit()
            }

        }

    }



}