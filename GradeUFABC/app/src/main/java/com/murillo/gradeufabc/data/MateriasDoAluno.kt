package com.murillo.gradeufabc.data

import androidx.room.Embedded
import androidx.room.Relation

data class MateriasDoAluno (
    @Embedded val aluno: Aluno,
    @Relation(
        parentColumn = "id",
        entityColumn = "idAluno"
    )
    val materias: List<Materia>
        ){

}