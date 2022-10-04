package com.generation.desenvolvmed.model

data class Medico(
    val id: Long,
    val crm: String,
    val cadastro: Cadastro,
    val postagens: List<Postagem>?
        ){
}