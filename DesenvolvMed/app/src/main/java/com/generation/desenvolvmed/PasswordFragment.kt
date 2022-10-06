package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentPasswordBinding
import com.generation.desenvolvmed.model.MedicoCadastro
import com.generation.desenvolvmed.model.PacienteCadastro
import java.util.*
import kotlin.concurrent.schedule


class PasswordFragment : Fragment() {

    private lateinit var binding: FragmentPasswordBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPasswordBinding.inflate(layoutInflater, container, false)

        //binding.radioButton.setOnClickListener{
            //binding.adicionalText.hint = "CRM"
        //}


        //if(binding.emailMudaSenha.text.isEmpty()){
        //    binding.adicionalText.visibility = GONE
        //} else{
            //binding.adicionalText.visibility = VISIBLE
        //}

        binding.buttonVerifica.setOnClickListener {
            verificaEmail()
        }

        binding.buttonAltera.setOnClickListener {
            cadastraNovaSenha()
        }

        return binding.root
    }

    private fun cadastraNovaSenha() {

        if(mainViewModel.medicoLogado.value?.body()?.crm is String){

            val cpf = binding.cpfMudaSenha.text.toString()
            val senha = binding.senhaNova.text.toString()
            val confereSenha = binding.senhaNovaAtt.text.toString()

            if(senha == confereSenha && cpf == mainViewModel.medicoLogado.value?.body()?.cadastro?.cpf.toString()){
                mainViewModel.attMedico(MedicoCadastro(
                    mainViewModel.medicoLogado.value?.body()?.id!!.toLong(),
                    cpf,
                    mainViewModel.medicoLogado.value?.body()?.cadastro!!.nome,
                    mainViewModel.medicoLogado.value?.body()?.cadastro!!.sobrenome,
                    senha,
                    mainViewModel.medicoLogado.value?.body()?.cadastro!!.email,
                    mainViewModel.medicoLogado.value?.body()?.crm!!
                )
                )

                mainViewModel.medicoLogado.value?.body()?.cadastro!!.senha = senha

                Toast.makeText(context, "Senha Medico alterada com sucesso", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_passwordFragment_to_loginFragment)
            } else{
                Toast.makeText(context, "CPF ou Senha incorretos", Toast.LENGTH_SHORT).show()
            }
        } else {

            val cpf = binding.cpfMudaSenha.text.toString()
            val senha = binding.senhaNova.text.toString()
            val confereSenha = binding.senhaNovaAtt.text.toString()

            if(senha == confereSenha && cpf == mainViewModel.pacienteLogado.value?.body()?.cadastro?.cpf.toString()){
                mainViewModel.attPaciente(PacienteCadastro(
                    mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                    cpf,
                    mainViewModel.pacienteLogado.value?.body()?.cadastro!!.nome,
                    mainViewModel.pacienteLogado.value?.body()?.cadastro!!.sobrenome,
                    senha,
                    mainViewModel.pacienteLogado.value?.body()?.cadastro!!.email,
                    mainViewModel.pacienteLogado.value?.body()?.convenio!!
                ))

                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.senha = senha

                Toast.makeText(context, "Senha Paciente alterada com sucesso", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_passwordFragment_to_loginFragment)
            } else{
                Toast.makeText(context, "CPF ou Senha incorretos", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun validaEmail(email : String) : Boolean {
        return (email.isNotBlank() && email.length in 2..255)
    }

    private fun verificaEmail() {
        val email = binding.emailMudaSenha.text.toString()

        if(validaEmail(email)){

            mainViewModel.getCadastroMedicoByEmail(email)
            mainViewModel.getCadastroPacienteByEmail(email)

            binding.cpfMudaSenha.visibility = VISIBLE
            binding.senhaNova.visibility = VISIBLE
            binding.senhaNovaAtt.visibility = VISIBLE
            binding.buttonAltera.visibility = VISIBLE

            mainViewModel.medicoLogado.observe(viewLifecycleOwner){
                    response -> if(response.body() != null){
                        Toast.makeText(context, "Cadastro de Médico Encontrado", Toast.LENGTH_SHORT).show()
                    }
            }

            mainViewModel.pacienteLogado.observe(viewLifecycleOwner){
                    response -> if(response.body() != null){
                        Toast.makeText(context, "Cadastro de Paciente Encontrado", Toast.LENGTH_SHORT).show()
                    }
            }

        } else{
            Toast.makeText(context, "Verifique o e-mail digitado", Toast.LENGTH_SHORT).show()
        }
    }

}