package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentUserProfileBinding


class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)

        var x = mainViewModel.pacienteLogado.value?.body()?.cadastro?.nome
        var y = " "
        var z = mainViewModel.pacienteLogado.value?.body()?.cadastro?.sobrenome
        var k = x + y + z
        binding.nomePacienteLogado.text = k

        binding.convenioPacienteLogado.text = mainViewModel.pacienteLogado.value?.body()?.convenio

        binding.buttonUpdatePacienteProfile.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_updateUserProfile)
        }

        return binding.root
    }

}