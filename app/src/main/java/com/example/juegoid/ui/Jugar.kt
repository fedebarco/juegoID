package com.example.juegoid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.juegoid.databinding.ActivityJugarBinding
import com.example.juegoid.ui.cJugar.ColoresFragment
import com.example.juegoid.ui.cJugar.Modell
import com.example.juegoid.ui.cJugar.PreguntaFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Jugar : AppCompatActivity() {
    var context=this
    val adaptadora= Paginaviewadapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityJugarBinding.inflate(layoutInflater)
        val model:Modell by viewModels()
        setContentView(binding.root)
        adaptadora.addFragment(ColoresFragment(),"tablero")
        adaptadora.addFragment(PreguntaFragment(),"Datos")
        binding.paginavista.adapter=adaptadora
        TabLayoutMediator(binding.tabs,binding.paginavista){tab,position->
            when(position){
                0->{tab.text="tablero"}
                1->{tab.text="datos"}
                2->{tab.text="dos"}
            }
        }.attach()
        model.acaba.observe(this,{
            val intentui = Intent(context, Final::class.java)
            startActivity(intentui)
        })

    }


    class Paginaviewadapter(manager: FragmentActivity): FragmentStateAdapter(manager) {
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, titulo: String) {
            fragmentList.add(fragment)
            titleList.add(titulo)
        }

        fun changeFragment(fragment: Fragment, titulo: String) {
            fragmentList[1] = fragment
            titleList[1] = titulo
        }
    }
}