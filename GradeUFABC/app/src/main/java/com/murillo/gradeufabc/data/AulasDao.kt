package com.murillo.gradeufabc.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*


@Dao
interface AulasDao {

    @Transaction
    @Query("SELECT * FROM aluno_table WHERE id= :id")
    fun getMateriasDoAluno(id: Long): List<MateriasDoAluno>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAluno(aluno: Aluno)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMateria(materia: Materia)

    @Query("SELECT * FROM aluno_table WHERE ra= :ra")
    fun getAlunoLogado(ra: String): Aluno
}