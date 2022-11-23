package com.murillo.gradeufabc

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.murillo.gradeufabc.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.time.LocalDate

class MainViewModel(application: Application): AndroidViewModel(application) {

    val repository : Repository

    var alunoLogado = MutableLiveData<Aluno>()

    var listagem = MutableLiveData<List<MateriasDoAluno>>()

    var posicao = MutableLiveData<Int>()

    var diaDeHoje = MutableLiveData<String>()

    init {
        val aulasDao = AulasDatabase.getDatabase(application).aulasDao()
        repository = Repository(aulasDao)
    }

    fun addAluno(aluno: Aluno){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAluno(aluno)
            alunoLogado.postValue(repository.getAlunoLogado(aluno.ra))
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
            listagem.postValue(response)
        }
    }

    fun getAlunoLogado(ra: String){
        viewModelScope.launch(Dispatchers.IO) {

            val valueAluno = repository.getAlunoLogado(ra)


            if(valueAluno != null){
                val valueListagem = repository.getMateriasDoAluno(valueAluno.id)
                listagem.postValue(valueListagem)
                alunoLogado.postValue(valueAluno)
            }

        }
    }

    @SuppressLint("NewApi")
    fun avaliaDia(){
        viewModelScope.launch {
            diaDeHoje.postValue(LocalDate.now().dayOfWeek.toString())
        }
    }

}