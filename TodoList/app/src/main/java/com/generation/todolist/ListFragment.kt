package com.generation.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.todolist.adapter.TarefaAdapter
import com.generation.todolist.adapter.TaskClickListener
import com.generation.todolist.databinding.FragmentListBinding
import com.generation.todolist.model.Categoria
import com.generation.todolist.model.Tarefa
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment(), TaskClickListener {

    private lateinit var binding: FragmentListBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(layoutInflater, container, false)

        mainViewModel.listTarefa()

        //val view = inflater.inflate(R.layout.fragment_list, container, false)

        //val floatingAdd = view.findViewById<FloatingActionButton>(R.id.floatingAdd)

        mainViewModel.myCategoriaResponse.observe(viewLifecycleOwner){
            Log.d("Requisicao", it.body().toString())
        }

        mainViewModel.tarefaCriada.observe(viewLifecycleOwner){
            Log.d("Tarefa", it.toString())
        }

        //Configuração do RecyclerView

        val adapter = TarefaAdapter(this, mainViewModel, requireContext())
        binding.recyclerTarefa.layoutManager = LinearLayoutManager(context)
        binding.recyclerTarefa.adapter = adapter
        binding.recyclerTarefa.setHasFixedSize(true)


        binding.floatingAdd.setOnClickListener{
            mainViewModel.tarefaSelecionada = null
            findNavController().navigate(R.id.action_listFragment_to_formFragment)
        }

        mainViewModel.myTarefaResponse.observe(viewLifecycleOwner){
            response -> if(response.body() != null){
                adapter.setList(response.body()!!)
            }
        }

        return binding.root
    }

    override fun onTaskClickListener(tarefa: Tarefa) {
        mainViewModel.tarefaSelecionada = tarefa
        findNavController().navigate(R.id.action_listFragment_to_formFragment)
    }

}