package com.murillo.gradeufabc

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.murillo.gradeufabc.data.Aluno
import com.murillo.gradeufabc.data.Materia
import com.murillo.gradeufabc.databinding.FragmentInicialBinding


class InicialFragment : Fragment() {

    private lateinit var binding: FragmentInicialBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private var ra = ""
    private var nome = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicialBinding.inflate(layoutInflater, container, false)

        binding.login.setOnClickListener {
            efetuaLogin()
        }

        binding.cadastro.setOnClickListener {
            findNavController().navigate(R.id.action_inicialFragment_to_cadastroFragment)
        }

        mainViewModel.avaliaDia()

        return binding.root
    }

    private fun efetuaLogin() {
        ra = binding.raLogin.text.toString()
        nome = binding.nomeLogin.text.toString()

        mainViewModel.getAlunoLogado(ra)


        mainViewModel.alunoLogado.observe(viewLifecycleOwner){
            response -> if(response.ra == ra && response.nome == nome && mainViewModel.diaDeHoje != null){
                findNavController().navigate(R.id.action_inicialFragment_to_gradeFragment)
            }
        }


    }


}