package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentDoctorProfileBinding


class DoctorProfileFragment : Fragment() {

    private lateinit var binding: FragmentDoctorProfileBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDoctorProfileBinding.inflate(layoutInflater, container, false)

        binding.crmMedicoLogado.text = mainViewModel.medicoLogado.value?.body()?.crm
        binding.nomeMedicoLogado.text = mainViewModel.medicoLogado.value?.body()?.cadastro?.nome
        binding.buttonUpdateMedico.setOnClickListener {
            findNavController().navigate(R.id.action_doctorProfileFragment_to_updateDoctorProfile)
        }
        return binding.root
    }

}