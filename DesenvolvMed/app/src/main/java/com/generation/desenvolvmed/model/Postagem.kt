package com.generation.desenvolvmed.model

data class Postagem (
    val id: Long,
    var titulo: String,
    var descricao: String,
    var anexo: String,
    val dataPostagem: String,
    val tema: Tema,
    val medico: Medico
        ) {
}