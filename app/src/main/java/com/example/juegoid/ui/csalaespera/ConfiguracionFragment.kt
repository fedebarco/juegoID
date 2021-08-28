package com.example.juegoid.ui.csalaespera

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.juegoid.databinding.FragmentConfiguracionBinding


class ConfiguracionFragment : Fragment() {

    private var _binding: FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!
    val model: VMSalaE by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentConfiguracionBinding.inflate(inflater,container,false)
        binding.switch1.isClickable=false
        binding.edio.isEnabled=false
        binding.edio.isFocusable=false
        binding.edio.keyListener=null
        binding.edio.inputType=InputType.TYPE_NULL
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        model.principal.observe(viewLifecycleOwner,{
            binding.switch1.isChecked = it
        })

    }


}