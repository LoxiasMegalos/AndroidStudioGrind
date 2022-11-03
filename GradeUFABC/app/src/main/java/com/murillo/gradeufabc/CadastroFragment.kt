package com.murillo.gradeufabc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.gradeufabc.data.Aluno
import com.murillo.gradeufabc.databinding.FragmentCadastroBinding
import com.murillo.gradeufabc.databinding.FragmentGradeBinding


class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBinding
    private val mainViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroBinding.inflate(layoutInflater, container, false)

        binding.buttonCadastro.setOnClickListener {
            realizaCadastro()
        }

        return binding.root
    }

    private fun realizaCadastro() {
        var nome = binding.nomeCadastro.text.toString()
        var sobrenome = binding.sobrenomeCadastro.text.toString()
        var ra = binding.raCadastro.text.toString()

        mainViewModel.addAluno(Aluno(0, nome, sobrenome, ra))

        mainViewModel.alunoLogado.observe(viewLifecycleOwner){
            if(mainViewModel.alunoLogado.value?.ra == ra && mainViewModel.alunoLogado.value?.nome == nome){
                Toast.makeText(context, "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT)
                findNavController().navigate(R.id.action_cadastroFragment_to_gradeFragment)
            }
        }
    }


}