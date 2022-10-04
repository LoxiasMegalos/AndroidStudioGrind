package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.generation.desenvolvmed.databinding.FragmentUserProfileBinding


class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)

        binding.nomePacienteLogado.text = mainViewModel.pacienteLogado.value?.body()?.cadastro?.nome
        binding.convenioPacienteLogado.text = mainViewModel.pacienteLogado.value?.body()?.convenio

        return binding.root
    }

}