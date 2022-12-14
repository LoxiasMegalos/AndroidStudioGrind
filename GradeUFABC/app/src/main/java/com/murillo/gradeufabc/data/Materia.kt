package com.murillo.gradeufabc.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materia_table")
data class Materia(
    @PrimaryKey(autoGenerate = true)
    val idM: Long,
    val nome: String,
    val professor: String,
    val sala: String,
    var primeiroDia: String,
    var horarioPrimeiroDia: String,
    var segundoDia: String?,
    var horarioSegundoDia: String?,
    val idAluno: Long
) {
}