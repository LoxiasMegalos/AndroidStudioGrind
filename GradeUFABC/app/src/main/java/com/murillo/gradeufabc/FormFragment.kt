package com.murillo.gradeufabc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murillo.gradeufabc.data.Materia
import com.murillo.gradeufabc.databinding.FragmentFormBinding

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private val mainViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFormBinding.inflate(layoutInflater, container, false)

        binding.spinnerSD.visibility = INVISIBLE
        binding.spinnerSH.visibility = INVISIBLE

        binding.checkAula.setOnClickListener {
            if(binding.checkAula.isChecked){
                binding.spinnerSD.visibility = VISIBLE
                binding.spinnerSH.visibility = VISIBLE
            } else{
                binding.spinnerSD.visibility = INVISIBLE
                binding.spinnerSH.visibility = INVISIBLE
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
        var ph = binding.spinnerPH.selectedItem.toString()
        var sd = binding.spinnerSD.selectedItem.toString()
        var sh = binding.spinnerSH.selectedItem.toString()

        val materiaAdd : Materia
        if(binding.checkAula.isChecked){
            materiaAdd = Materia(0, materia, professor, sala, pd, ph, sd, sh,
                mainViewModel.alunoLogado.value!!.id)
            mainViewModel.addMateria(materiaAdd)

        } else{
            materiaAdd = Materia(0, materia, professor, sala, pd, ph,null, null,
                mainViewModel.alunoLogado.value!!.id)
            mainViewModel.addMateria(materiaAdd)
        }

        mainViewModel.getListaDeMaterias(mainViewModel.alunoLogado.value!!.id)

        mainViewModel.listagem.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_formFragment_to_gradeFragment)
        }
    }


}