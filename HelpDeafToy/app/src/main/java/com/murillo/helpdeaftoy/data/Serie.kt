package com.murillo.helpdeaftoy.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "serie_table")
class Serie(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var nome: String,
    var numeroSerie: String
) {
}