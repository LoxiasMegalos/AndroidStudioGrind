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
        binding = FragmentGradeBinding.inflate(layoutInflater, container, false)

        binding.addMateria.setOnClickListener{
            findNavController().navigate(R.id.action_gradeFragment_to_formFragment)
        }

        binding.raAluno.text = mainViewModel.alunoLogado.value?.ra

        if(mainViewModel.listagem.value != null){
            getlistagem()
        }

        return binding.root
    }

    @SuppressLint("NewApi")
    private fun getlistagem() {

        val adapter = MateriaAdapter(mainViewModel)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        var idProcurado = mainViewModel.alunoLogado.value?.id
        var materia = emptyList<Materia>()
        var materiasAdapter = mutableListOf<Materia>()

        for(i in mainViewModel.listagem.value!!){
            if(i.aluno.id == idProcurado){
                materia = i.materias
                break
            }
        }
        var diaDeHoje = LocalDate.now().dayOfWeek.toString()

        for(m in materia){
            m.primeiroDia = converteDia(m.primeiroDia)
            if(m.segundoDia != null){
                m.segundoDia = converteDia(m.segundoDia!!)
            }
            if(m.primeiroDia == diaDeHoje || m.segundoDia == diaDeHoje){
                materiasAdapter.add(m)
            }
        }

        println(diaDeHoje)

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