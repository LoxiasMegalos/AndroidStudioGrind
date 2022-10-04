package com.generation.desenvolvmed.model

data class Tema(
    val id: Long,
    var tema: String?,
    val postagens: List<Postagem>?
) {
}