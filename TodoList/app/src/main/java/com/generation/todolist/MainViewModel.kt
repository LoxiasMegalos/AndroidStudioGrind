package com.generation.todolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.todolist.api.Repository
import com.generation.todolist.model.Categoria
import com.generation.todolist.model.Tarefa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var tarefaSelecionada: Tarefa? = null

    //val repository = Repository()

    private val _myCategoriaResponse = MutableLiveData<Response<List<Categoria>>>()
    val myCategoriaResponse: LiveData<Response<List<Categoria>>> = _myCategoriaResponse

    private val _myTarefaResponse = MutableLiveData<Response<List<Tarefa>>>()

    val myTarefaResponse: LiveData<Response<List<Tarefa>>> = _myTarefaResponse

    val dataSelecionada = MutableLiveData<LocalDate>()

    private val _tarefaCriada = MutableLiveData<Response<Tarefa>>()
    val tarefaCriada: LiveData<Response<Tarefa>> = _tarefaCriada

    fun listCategoria() {
        viewModelScope.launch {
            try {
                val response = repository.listCategoria()
                _myCategoriaResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }

        }
    }

    fun addTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                val response = repository.addTarefa(tarefa)
                _tarefaCriada.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listTarefa() {
        viewModelScope.launch {
            try {
                val response = repository.listTarefa()
                _myTarefaResponse.value = response
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun updateTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            try {
                repository.updateTarefa(tarefa)
                listTarefa()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun deleteTarefa(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteTarefa(id)
                listTarefa()
            } catch (e: Exception) {
                Log.d("Erro", e.message.toString())
            }
        }
    }

}