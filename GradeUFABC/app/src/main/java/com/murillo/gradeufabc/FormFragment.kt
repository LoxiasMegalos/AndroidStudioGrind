package com.murillo.gradeufabc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.gradeufabc.data.Materia
import com.murillo.gradeufabc.databinding.FragmentCadastroBinding
import com.murillo.gradeufabc.databinding.FragmentFormBinding
import com.murillo.gradeufabc.databinding.FragmentGradeBinding

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        binding.spinnerSD.visibility = INVISIBLE

        binding.checkAula.setOnClickListener {
            if(binding.checkAula.isChecked){
                binding.spinnerSD.visibility = VISIBLE
            } else{
                binding.spinnerSD.visibility = INVISIBLE
            }
        }

        binding.buttonCadastroMateria.setOnClickListener {
            realizaCadastro()
        }

        return binding.root
    }

    private fun realizaCadastro() {
        var materia = binding.nomeMateria.text.toString()
        var professor = binding.professorMateria.text.toString()
        var sala = binding.salaMateria.text.toString()
        var pd = binding.spinnerPD.selectedItem.toString()
        var sd = binding.spinnerSD.selectedItem.toString()

        if(binding.checkAula.isChecked){
            mainViewModel.addMateria(Materia(0, materia, professor, sala, pd, sd,
            mainViewModel.alunoLogado.value!!.id))

        } else{
            mainViewModel.addMateria(Materia(0, materia, professor, sala, pd, null,
                mainViewModel.alunoLogado.value!!.id))
        }

        mainViewModel.getListaDeMaterias(mainViewModel.alunoLogado.value!!.id)

        mainViewModel.listagem.observe(viewLifecycleOwner){
            if(mainViewModel.listagem.value!!.isNotEmpty()){
                findNavController().navigate(R.id.action_formFragment_to_gradeFragment)
            }
        }
    }


}