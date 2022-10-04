package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentLoginBinding
import com.generation.desenvolvmed.databinding.FragmentSelSignUpBinding


class SelSignUpFragment : Fragment() {

    private lateinit var binding: FragmentSelSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelSignUpBinding.inflate(layoutInflater, container, false)


        binding.cadastroMedico.setOnClickListener {
            findNavController().navigate(R.id.action_selSignUpFragment_to_doctorSignUpFragment)
        }

        binding.cadastroPaciente.setOnClickListener {
            findNavController().navigate(R.id.action_selSignUpFragment_to_userSignUpFragment)
        }

        return binding.root
    }

}