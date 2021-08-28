package com.example.juegoid.ui.cJugar


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.juegoid.databinding.FragmentColoresBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ColoresFragment : Fragment() {
    @Inject lateinit var dep: String
    private lateinit var database: DatabaseReference
    private var _binding: FragmentColoresBinding? = null
    private val binding get() = _binding!!
    val model: Modell by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentColoresBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database.reference
        var des2=mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)

        binding.uno.setOnClickListener {
            estaEn(1,des2)

        }
        binding.dos.setOnClickListener {
            estaEn(2,des2)
        }
        binding.tres.setOnClickListener {
            estaEn(3,des2)
        }
        binding.cuatro.setOnClickListener {
            estaEn(4,des2)
        }
        binding.cinco.setOnClickListener {
            estaEn(5,des2)
        }
        binding.seis.setOnClickListener {
            estaEn(6,des2)
        }
        binding.siete.setOnClickListener {
            estaEn(7,des2)
        }
        binding.ocho.setOnClickListener {
            estaEn(8,des2)
        }
        binding.nueve.setOnClickListener {
            estaEn(9,des2)
        }
        binding.diez.setOnClickListener {
            estaEn(10,des2)
        }
        binding.once.setOnClickListener {
            estaEn(11,des2)
        }
        binding.doce.setOnClickListener {
            estaEn(12,des2)
        }
        binding.trece.setOnClickListener {
            estaEn(13,des2)
        }
        binding.catorce.setOnClickListener {
            estaEn(14,des2)
        }
        binding.quince.setOnClickListener {
            estaEn(15,des2)
        }
        binding.diezseis.setOnClickListener {
            estaEn(16,des2)
        }

        model.sUsu.observe(viewLifecycleOwner,{
            val nombres= mutableListOf<String>()
            val valor= mutableListOf<Int>()
            for (i in it){
                nombres.add(i.noUsuario)
                valor.add(i.dinero)
                val adaptadora = ColorAdapter(nombres,valor,view.context)
                binding.todasb.adapter = adaptadora
            }
        })

        database.child("cadena").child(dep).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                des2= mutableListOf()
                for (postSnapshot in snapshot.children){
                    des2.add(postSnapshot.getValue<Int>()!!)
                    model.iniciaColores.postValue(model.desordenaPrimer())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }




    fun estaEn(n:Int,reformado:MutableList<Int>){
        //val aleatorios2= mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
        var nuVa=model.colorVa.value!!
        val colorcillos2= arrayOf<String>("#FFFFFF","#3700B3","#CC0000","#FF8800","#FFFC33","#3933FF","#DAF7A6","#94FF33","#FF33C3","#018786","#00DDFF","#AAAAAA","#8B33FF","#99CC00","#581845","#FF3656")
        val ale= reformado[nuVa]
        val colo=ColorDrawable(Color.parseColor(colorcillos2[ale-1]))
        //binding.activos.text=ale.toString()
        if (ale==1){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==2){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==3){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==4){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==5){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==6){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==7){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==8){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==9){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==10){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==11){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==12){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==13){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==14){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==15){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }else if (ale==16){
            if (ale==n){
                nuuvaes(n,nuVa,colo)
                nuVa+=1
            }
        }
        model.colorVa.postValue(nuVa)
        val dinero3=model.pUsu.value!!.dinero-1
        model.actualizadinero(dinero3)
    }
    fun nuuvaes(n:Int,nuu:Int,coll:ColorDrawable){
        if(nuu==0){
            binding.primer.background=coll
            binding.primer.text=n.toString()
        }else if(nuu==1){
            binding.segundo.background=coll
            binding.segundo.text=n.toString()
        }else if(nuu==2){
            binding.tercero.background=coll
            binding.tercero.text=n.toString()
        }else if(nuu==3){
            binding.cuarto.background=coll
            binding.cuarto.text=n.toString()
        }else if(nuu==4){
            binding.quinto.background=coll
            binding.quinto.text=n.toString()
        }else if(nuu==5){
            binding.sexto.background=coll
            binding.sexto.text=n.toString()
        }else if(nuu==6){
            binding.septimo.background=coll
            binding.septimo.text=n.toString()
        }else if(nuu==7){
            binding.octavo.background=coll
            binding.octavo.text=n.toString()
        }else if(nuu==8){
            binding.noveno.background=coll
            binding.noveno.text=n.toString()
            model.acaba.postValue(true)
        }


    }
}