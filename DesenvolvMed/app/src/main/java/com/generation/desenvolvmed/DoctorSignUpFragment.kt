package com.generation.desenvolvmed

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    var _email = ""
    var sucessoCadastro = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDoctorSignUpBinding.inflate(layoutInflater, container, false)

        binding.buttonDoctorFeed.setOnClickListener {

            cadastraMedico()
            Timer().schedule(2000) {

            }
            if(sucessoCadastro){
                mainViewModel.getCadastroMedicoByEmail(_email)
                vaiProFeed(true)
            } else{
                Toast.makeText(context, "E-mail ja cadastrado, faça log in", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_doctorSignUpFragment_to_loginFragment)
            }
        }

        return binding.root
    }

    private fun validarCampos(nome: String, cpf: String, sobrenome: String, email: String, senha: String, crm: String): Boolean {

        return (
                            (nome.isNotBlank() && nome.length in 1..255) &&
                            (cpf.isNotBlank() && cpf.length == 11) &&
                            (sobrenome.isNotBlank() && sobrenome.length in 1..255) &&
                            (email.isNotBlank() && email.length in 1..255) &&
                            (senha.isNotBlank() && senha.length in 1..255) &&
                                    (crm.isNotBlank() && crm.length == 13)
                )
    }

    private fun validarEmail(email: String) : Boolean{

        mainViewModel.getCadastroMedicoByEmail(email)
        if(mainViewModel.medicoLogado.value?.body()?.crm != null){
            Toast.makeText(context, "E-mail ja cadastrado", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun cadastraMedico() {
        val nome = binding.nomeCadastro.text.toString()
        val cpf = binding.cpfCadastro.text.toString()
        val sobrenome = binding.sobrenomeCadastro.text.toString()
        var email = binding.emailCadastro.text.toString()
        val senha = binding.senhaCadastro.text.toString()
        val crm = binding.crmCadastro.text.toString()

        if(validarCampos(nome, cpf, sobrenome, email, senha, crm) && validarEmail(email)){

            mainViewModel.addMedico(MedicoCadastro(0, cpf, nome, sobrenome, senha, email, crm), email)
            _email = email
            sucessoCadastro = true

        } else {
            _email = ""
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun vaiProFeed(valor: Boolean){

        findNavController().navigate(R.id.action_doctorSignUpFragment_to_doctorFeedFragment)

    }

}