package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentUserSignUpBinding
import com.generation.desenvolvmed.model.PacienteCadastro
import java.util.*
import kotlin.concurrent.schedule

class UserSignUpFragment : Fragment() {

    private lateinit var binding : FragmentUserSignUpBinding
    private val mainViewModel:MainViewModel by activityViewModels()
    var email = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserSignUpBinding.inflate(layoutInflater, container, false)



        binding.buttonUserFeed.setOnClickListener {
            cadastraPaciente()

            Timer().schedule(1000){
                mainViewModel.getCadastroPacienteByEmail(email)
            }
            mainViewModel.pacienteLogado.observe(viewLifecycleOwner){
                    response -> if(response.body() != null){
                        findNavController().navigate(R.id.action_userSignUpFragment_to_userFeedFragment)
                    } else{
                        Toast.makeText(context, "Dados de cadastro já utilizados! Tente novamente", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        return binding.root
    }

    private fun validarCampos(nome: String, cpf: String, sobrenome: String, email: String, senha: String, convenio: String): Boolean {
        return (
                (nome.isNotBlank() && nome.length in 1..255) &&
                        (cpf.isNotBlank() && cpf.length == 11) &&
                        (sobrenome.isNotBlank() && sobrenome.length in 1..255) &&
                        (email.isNotBlank() && email.length in 1..255) &&
                        (senha.isNotBlank() && senha.length in 1..255) &&
                        (convenio.isEmpty() || convenio.length > 1)
                )
    }

    private fun cadastraPaciente() {
        val nome = binding.nomeCadastro.text.toString()
        val cpf = binding.cpfCadastro.text.toString()
        val sobrenome = binding.sobrenomeCadastro.text.toString()
        email = binding.emailCadastro.text.toString()
        val senha = binding.senhaCadastro.text.toString()
        val convenio = binding.convenioCadastro.text.toString()

        if(validarCampos(nome, cpf, sobrenome, email, senha, convenio)){
            mainViewModel.addPaciente(PacienteCadastro(0, cpf, nome, sobrenome, senha, email, convenio))
        } else {
            Toast.makeText(context, "Verifique os campos!", Toast.LENGTH_SHORT).show()
        }
    }

}