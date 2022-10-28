package com.murillo.helpdeaftoy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.helpdeaftoy.databinding.FragmentInfosBinding

class InfosFragment : Fragment() {

    private lateinit var binding: FragmentInfosBinding
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfosBinding.inflate(layoutInflater, container, false)

        binding.textNomeUser.text = mainViewModel.serieLogada.value?.nome.toString()
        binding.textSerialNumberUser.text = mainViewModel.serieLogada.value?.numeroSerie.toString()

        binding.perfilButton.setOnClickListener {
            findNavController().navigate(R.id.action_infosFragment_to_perfilFragment)
        }

        return binding.root
    }

}