package com.generation.desenvolvmed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.desenvolvmed.databinding.FragmentDoctorFeedBinding
import com.generation.desenvolvmed.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)


        binding.buttonLogin.setOnClickListener {
            realizarLogin()
        }

        binding.buttonCadastros.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_selSignUpFragment)
        }

        binding.buttonSenha.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_passwordFragment)
        }

        return binding.root
    }

    private fun validaLogin(email: String, senha: String): Boolean {
        return (
                    (!email.isNullOrBlank() || email.length in 0..255 ) &&
                            (!senha.isNullOrBlank() || senha.length in 3..255)
                )
    }

    private fun realizarLogin() {

        val email = binding.loginEmail.text.toString()
        val senha = binding.loginSenha.text.toString()

        if(validaLogin(email, senha)){

            mainViewModel.getCadastroMedicoByEmail(email)
            mainViewModel.getCadastroPacienteByEmail(email)

            mainViewModel.medicoLogado.observe(viewLifecycleOwner){
                response -> if(response.body() != null){
                    if(senha == mainViewModel.medicoLogado.value?.body()?.cadastro?.senha){
                        Toast.makeText(context, "Login de medico Bem Sucedido", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_loginFragment_to_doctorFeedFragment)
                    }
                    else{
                        Toast.makeText(context, "Senha Incorreta", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            mainViewModel.pacienteLogado.observe(viewLifecycleOwner){
                    response -> if(response.body() != null){
                        if(senha == mainViewModel.pacienteLogado.value?.body()?.cadastro?.senha){
                            Toast.makeText(context, "Login de paciente Bem Sucedido", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_loginFragment_to_userFeedFragment)
                        }
                        else{
                            Toast.makeText(context, "Senha Incorreta", Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        } else{
            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
        }
    }


}