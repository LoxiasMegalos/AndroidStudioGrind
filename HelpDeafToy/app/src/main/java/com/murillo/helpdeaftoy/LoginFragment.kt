package com.murillo.helpdeaftoy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.helpdeaftoy.data.Serie
import com.murillo.helpdeaftoy.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.button.setOnClickListener {
            realizaLogin()
        }

        return binding.root
    }

    private fun realizaLogin() {

        var nome = binding.textNome.text.toString()
        var serialNumber = binding.textSerialNumber.text.toString()

        if(validaLogin(nome, serialNumber)){
            val serie = Serie(0, nome, serialNumber)
            mainViewModel.addSerie(serie)

            mainViewModel.selectSeries.observe(viewLifecycleOwner){
                if(mainViewModel.selectSeries.value?.size!! > 0){
                    mainViewModel.serieLogada.value = serie
                }
            }

            mainViewModel.serieLogada.observe(viewLifecycleOwner){
                if(mainViewModel.serieLogada.value?.nome.toString() == nome){
                    Toast.makeText(context, "Login Realizado com Sucesso!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_infosFragment)
                }
            }

        } else{
            Toast.makeText(context, "Dados Inválidos!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validaLogin(nome: String, serialNumber: String): Boolean {
        return nome.length > 5 && nome.isNotEmpty()
                && serialNumber.length == 8 && serialNumber.isNotEmpty()
    }

}