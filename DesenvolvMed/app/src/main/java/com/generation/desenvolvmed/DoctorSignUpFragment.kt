package com.generation.desenvolvmed

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentDoctorSignUpBinding
import com.generation.desenvolvmed.databinding.FragmentSelSignUpBinding
import com.generation.desenvolvmed.model.Medico
import com.generation.desenvolvmed.model.MedicoCadastro
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule


class DoctorSignUpFragment : Fragment() {

    private lateinit var binding: FragmentDoctorSignUpBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    var email = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDoctorSignUpBinding.inflate(layoutInflater, container, false)

        binding.buttonDoctorFeed.setOnClickListener {
            cadastraMedico()

            Timer().schedule(1000){
                mainViewModel.getCadastroMedicoByEmail(email)
            }
            mainViewModel.medicoLogado.observe(viewLifecycleOwner){
                    response -> if(response.body() != null){
                findNavController().navigate(R.id.action_doctorSignUpFragment_to_doctorFeedFragment)
            }
            }
        }

        return binding.root
    }

    private fun validarCampos(nome: String, cpf: String, sobrenome: String, email: String, senha: String, crm: String): Boolean {
        return true
    }

    private fun cadastraMedico() {
        val nome = binding.nomeCadastro.text.toString()
        val cpf = binding.cpfCadastro.text.toString()
        val sobrenome = binding.sobrenomeCadastro.text.toString()
        email = binding.emailCadastro.text.toString()
        val senha = binding.senhaCadastro.text.toString()
        val crm = binding.crmCadastro.text.toString()

        if(validarCampos(nome, cpf, sobrenome, email, senha, crm)){
            mainViewModel.addMedico(MedicoCadastro(0, cpf, nome, sobrenome, senha, email, crm))
        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }
    }



}