package com.generation.desenvolvmed.model

data class Cadastro(
    val id: Long,
    val cpf: String,
    var nome: String,
    var sobrenome: String,
    var senha: String,
    var email: String,
    val comentarios: List<Comentario>?
        ){
}