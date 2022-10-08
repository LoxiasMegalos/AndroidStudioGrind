package com.generation.desenvolvmed

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentUpdateUserProfileBinding
import com.generation.desenvolvmed.model.PacienteCadastro


class updateUserProfile : Fragment() {

    private lateinit var binding: FragmentUpdateUserProfileBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentUpdateUserProfileBinding.inflate(layoutInflater, container, false)

        binding.buttonConfirmaUpdate.setOnClickListener {
            updatePerfilUsuario()
            findNavController().navigate(R.id.action_updateUserProfile_to_userProfileFragment)
        }

        return binding.root
    }
    private fun camposValidadosTotal(nome : String, sobrenome: String, email:String, senha:String, convenio: String): Boolean {
        return ((nome.isNotBlank() && nome.length in 1..255) &&
                (sobrenome.isNotBlank() && sobrenome.length in 1..255) &&
                (email.isNotBlank() && email.length in 1..255) &&
                (senha.isNotBlank() && senha.length in 1..255) &&
                (convenio.isNotBlank() || convenio.length > 1)
                )
    }

    private fun updatePerfilUsuario() {
        var nome = binding.textNovoNome.text.toString()
        var sobrenome = binding.textNovoSobrenome.text.toString()
        var email = binding.textNovoEmail.text.toString()
        var senha = binding.textNovaSenha.text.toString()
        var convenio = binding.textNovoConvenio.text.toString()


        if(camposValidadosTotal(nome, sobrenome, email, senha, convenio)){
            mainViewModel.attPaciente(PacienteCadastro(
                mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.cpf,
                nome,
                sobrenome,
                senha,
                email,
                convenio
            ))
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.nome = nome
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.sobrenome = sobrenome
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.email = email
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.senha = senha
            mainViewModel.pacienteLogado.value?.body()?.convenio = convenio
            Toast.makeText(context, "Tudo foi atualizado com sucesso", Toast.LENGTH_SHORT).show()
        } else if(camposValidadosParcial(nome, sobrenome, email, senha)){
            mainViewModel.attPaciente(PacienteCadastro(
                mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.cpf,
                nome,
                sobrenome,
                senha,
                email,
                mainViewModel.pacienteLogado.value?.body()?.convenio!!
            ))
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.nome = nome
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.sobrenome = sobrenome
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.email = email
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.senha = senha
            Toast.makeText(context, "Nome, sobrenome,email e senha atualizado com sucesso", Toast.LENGTH_SHORT).show()
        } else if(camposValidadosSemSenha(nome, sobrenome, email)){
            mainViewModel.attPaciente(PacienteCadastro(
                mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.cpf,
                nome,
                sobrenome,
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.senha,
                email,
                mainViewModel.pacienteLogado.value?.body()?.convenio!!
            ))
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.nome = nome
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.sobrenome = sobrenome
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.email = email
            Toast.makeText(context, "Nome, sobrenome e email atualizado com sucesso", Toast.LENGTH_SHORT).show()
        } else if(camposValidadosSemEmail(nome, sobrenome)){
            mainViewModel.attPaciente(PacienteCadastro(
                mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.cpf,
                nome,
                sobrenome,
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.senha,
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.email,
                mainViewModel.pacienteLogado.value?.body()?.convenio!!
            ))
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.nome = nome
            mainViewModel.pacienteLogado.value?.body()?.cadastro!!.sobrenome = sobrenome
            Toast.makeText(context, "Nome e sobrenome atualizado com sucesso", Toast.LENGTH_SHORT).show()
        } else if(convenioValidado(convenio)){
            mainViewModel.attPaciente(PacienteCadastro(
                mainViewModel.pacienteLogado.value?.body()?.id!!.toLong(),
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.cpf,
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.nome,
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.sobrenome,
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.senha,
                mainViewModel.pacienteLogado.value?.body()?.cadastro!!.email,
                convenio
            ))
            mainViewModel.pacienteLogado.value?.body()?.convenio = convenio
            Toast.makeText(context, "Dados Atualizados com sucesso", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "Dados Invalidos", Toast.LENGTH_SHORT).show()
        }
    }


    private fun convenioValidado(convenio: String): Boolean {
        return (convenio.isNotBlank() || convenio.length > 1)
    }

    private fun camposValidadosSemEmail(nome: String, sobrenome: String): Boolean {
        return ((nome.isNotBlank() && nome.length in 1..255) &&
                (sobrenome.isNotBlank() && sobrenome.length in 1..255)
                )
    }

    private fun camposValidadosSemSenha(nome: String, sobrenome: String, email: String): Boolean {
        return ((nome.isNotBlank() && nome.length in 1..255) &&
                (sobrenome.isNotBlank() && sobrenome.length in 1..255) &&
                (email.isNotBlank() && email.length in 1..255)
                )
    }

    private fun camposValidadosParcial(nome: String, sobrenome: String, email: String, senha: String): Boolean {
        return ((nome.isNotBlank() && nome.length in 1..255) &&
                (sobrenome.isNotBlank() && sobrenome.length in 1..255) &&
                (email.isNotBlank() && email.length in 1..255) &&
                (senha.isNotBlank() && senha.length in 1..255)
                )
    }


}