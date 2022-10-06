package com.generation.desenvolvmed.model

data class Cadastro(
    val id: Long,
    val cpf: String,
    val nome: String,
    val sobrenome: String,
    var senha: String,
    val email: String,
    val comentarios: List<Comentario>?
        ){
}