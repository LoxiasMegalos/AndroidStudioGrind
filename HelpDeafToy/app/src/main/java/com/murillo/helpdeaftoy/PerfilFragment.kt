package com.murillo.helpdeaftoy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.helpdeaftoy.databinding.FragmentInfosBinding
import com.murillo.helpdeaftoy.databinding.FragmentPerfilBinding


class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPerfilBinding.inflate(layoutInflater, container, false)

        binding.userLogado.text = mainViewModel.serieLogada.value?.nome.toString()
        binding.numeroLogado.text = mainViewModel.serieLogada.value?.numeroSerie.toString()

        binding.buttonLogout.setOnClickListener {
            realizaLogout()
        }

        return binding.root
    }

    private fun realizaLogout() {

        mainViewModel.nukeTable()

        mainViewModel.selectSeries.observe(viewLifecycleOwner){
            response -> if(response.isEmpty()){
                findNavController().navigate(R.id.action_perfilFragment_to_splashScreenFragment)
            }
        }
    }

}