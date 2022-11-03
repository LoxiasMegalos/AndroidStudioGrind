package com.murillo.gradeufabc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.murillo.gradeufabc.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {

    val repository : Repository

    var alunoLogado = MutableLiveData<Aluno>()

    var listagem = MutableLiveData<List<MateriasDoAluno>>()

    init {
        val aulasDao = AulasDatabase.getDatabase(application).aulasDao()
        repository = Repository(aulasDao)
    }

    fun addAluno(aluno: Aluno){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAluno(aluno)
            alunoLogado = MutableLiveData(repository.getAlunoLogado(aluno.ra))
        }
    }

    fun addMateria(materia: Materia){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMateria(materia)
        }
    }

    fun getListaDeMaterias(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getMateriasDoAluno(id)
            listagem = MutableLiveData(response)

        }
    }

    fun getAlunoLogado(ra: String){
        viewModelScope.launch(Dispatchers.IO) {
            alunoLogado = MutableLiveData(repository.getAlunoLogado(ra))
            if(alunoLogado.value != null){
                listagem = MutableLiveData(repository.getMateriasDoAluno(alunoLogado.value!!.id))
            }
        }
    }

}