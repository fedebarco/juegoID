package com.example.juegoid.ui.csalaespera

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.juegoid.databinding.FragmentEsperarBinding


class EsperarFragment : Fragment() {




    private var _binding: FragmentEsperarBinding? = null
    private val binding get() = _binding!!
    val model: VMSalaE by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentEsperarBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storage=view.context.getSharedPreferences("midb",0)

        model.codigoJuego.observe(viewLifecycleOwner,{
            storage.edit().putString("codigo",it).apply()
            binding.cood.text=it
        })

        model.nombresus.observe(viewLifecycleOwner,{
            val adaptadora = ArrayAdapter<String>(view.context, R.layout.simple_list_item_1, it)
            binding.todasb.adapter = adaptadora
        })
        //objetos llamada




        binding.newGame.setOnClickListener {
            model.comandoInicia(model.principal.value!!)
        }
    }

}