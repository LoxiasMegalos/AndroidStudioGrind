package com.generation.desenvolvmed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.generation.desenvolvmed.api.Repository
import com.generation.desenvolvmed.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
        ) : ViewModel(){

    private val _myTemaResponse = MutableLiveData<Response<List<Tema>>>()
    val myTemaResponse : LiveData<Response<List<Tema>>> = _myTemaResponse

    val pacienteLogado = MutableLiveData<Response<Paciente>>()

    var medicoLogado = MutableLiveData<Response<Medico>>()

    private val _myPostagemResponse = MutableLiveData<Response<List<Postagem>>>()
    val myPostagemResponse : LiveData<Response<List<Postagem>>> = _myPostagemResponse


    fun getCadastroPacienteByEmail(email: String){
        viewModelScope.launch {
            try{
                val response = repository.getCadastroPacienteByEmail(email)
                pacienteLogado.value = response
            } catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }


    fun getCadastroMedicoByEmail(email: String){
        viewModelScope.launch {
            try{
                val response = repository.getCadastroMedicoByEmail(email)
                medicoLogado.value = response
            } catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listTemas(){
        viewModelScope.launch {
            try {
                val response = repository.listTemas()
                _myTemaResponse.value = response
            } catch (e : Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addPostagem(postagem: Postagem){
        viewModelScope.launch {
            try{
                repository.addPostagem(postagem)
            } catch(e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun listPostagem(){
        viewModelScope.launch{
            try{
                val response = repository.listPostagem()
                _myPostagemResponse.value = response
            } catch(e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addMedico(medico: MedicoCadastro){
        viewModelScope.launch {
            try {
                repository.addMedico(medico)
            } catch (e : Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }

    fun addPaciente(paciente: Paciente){
        viewModelScope.launch {
            try{
                repository.addPaciente(paciente)
            } catch (e: Exception){
                Log.d("Erro", e.message.toString())
            }
        }
    }
}