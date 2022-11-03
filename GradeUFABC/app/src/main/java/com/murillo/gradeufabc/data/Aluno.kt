package com.murillo.gradeufabc.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aluno_table")
data class Aluno (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nome: String,
    val sobrenome: String,
    val ra: String
        ){
}