package com.generation.dicefragments

class JogandoDados {


    companion object{
        fun jogandoDados(opcao : Int) : String{

            var numeroObtido = (1..opcao).random()

            return numeroObtido.toString()

        }
    }

}