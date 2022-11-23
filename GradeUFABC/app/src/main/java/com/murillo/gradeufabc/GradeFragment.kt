package com.murillo.gradeufabc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.murillo.gradeufabc.adapter.MateriaAdapter
import com.murillo.gradeufabc.data.Materia
import com.murillo.gradeufabc.databinding.FragmentGradeBinding
import java.time.LocalDate


class GradeFragment : Fragment() {

    private lateinit var binding: FragmentGradeBinding
    private val mainViewModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.avaliaDia()

        binding = FragmentGradeBinding.inflate(layoutInflater, container, false)

        binding.addMateria.setOnClickListener{
            findNavController().navigate(R.id.action_gradeFragment_to_formFragment)
        }

        binding.raAluno.text = mainViewModel.alunoLogado.value?.ra


        mainViewModel.listagem.observe(viewLifecycleOwner){
            response -> if(response != null){
                getlistagem()
            }
        }

        return binding.root
    }

    private fun getlistagem() {

        val adapter = MateriaAdapter(mainViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        var idProcurado = mainViewModel.alunoLogado.value?.id
        var materia = emptyList<Materia>()
        var materiasAdapter = mutableListOf<Materia>()
        var index = 0

        for(i in mainViewModel.listagem.value!!){
            index += 1
            if(i.aluno.id == idProcurado){
                mainViewModel.posicao.postValue(index)
                materia = i.materias
                break
            }
        }


        for(m in materia){
            m.primeiroDia = converteDia(m.primeiroDia)
            if(m.segundoDia != null){
                m.segundoDia = converteDia(m.segundoDia!!)
            }
            if(m.primeiroDia == mainViewModel.diaDeHoje.value.toString() || m.segundoDia == mainViewModel.diaDeHoje.value.toString()){
                materiasAdapter.add(m)
            }
        }

        adapter.setList(materiasAdapter)

    }

    private fun converteDia(diaDaMateria: String): String {
        if(diaDaMateria == "Segunda"){
            return "MONDAY"
        } else if(diaDaMateria == "Terça"){
            return "TUESDAY"
        } else if(diaDaMateria == "Quarta"){
            return "WEDNESDAY"
        } else if(diaDaMateria == "Quinta"){
            return "THURSDAY"
        } else{
            return "FRIDAY"
        }
    }

}