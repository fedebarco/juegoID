package com.example.juegoid.ui.cJugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.juegoid.databinding.FragmentPreguntaBinding


class PreguntaFragment() : Fragment() {
    private var _binding: FragmentPreguntaBinding? = null
    private val binding get() = _binding!!

    val model: Modell by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
    : View {
        _binding= FragmentPreguntaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.envia.setOnClickListener {
            model.enviaPregunta(binding.pregun.text.toString())
        }

        model.questions.observe(viewLifecycleOwner,{
            if(!it.isNullOrEmpty()){
                val adaptadora= PreguntaAdapter(it,model,view.context)
                binding.cpre.adapter=adaptadora
            }
        })
    }

}