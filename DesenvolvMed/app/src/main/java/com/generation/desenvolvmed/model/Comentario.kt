package com.generation.desenvolvmed.model

data class Comentario (
    val id: Long,
    var conteudo: String,
    val dataComentario: String,
    val postagem: Postagem,
    val cadastro: Cadastro
        ){
}