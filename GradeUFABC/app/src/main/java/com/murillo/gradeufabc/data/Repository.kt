package com.murillo.gradeufabc.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Repository(private val aulasDao: AulasDao) {


    fun addAluno(aluno: Aluno){
        aulasDao.addAluno(aluno)
    }

    fun addMateria(materia: Materia){
        aulasDao.addMateria(materia)
    }

    fun getMateriasDoAluno(id: Long) : List<MateriasDoAluno> = aulasDao.getMateriasDoAluno(id)

    fun getAlunoLogado(ra: String) : Aluno = aulasDao.getAlunoLogado(ra)

}