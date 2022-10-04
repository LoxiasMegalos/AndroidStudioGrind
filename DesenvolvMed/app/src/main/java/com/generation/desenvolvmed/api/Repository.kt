package com.generation.desenvolvmed.api

import com.generation.desenvolvmed.model.*
import retrofit2.Response

class Repository {

    suspend fun getCadastroPacienteByEmail(email: String): Response<Paciente> {
        return RetrofitInstance.api.getCadastroPacienteByEmail(email)
    }

    suspend fun getCadastroMedicoByEmail(email: String): Response<Medico> {
        return RetrofitInstance.api.getCadastroMedicoByEmail(email)
    }

    suspend fun addPaciente(paciente: Paciente): Response<Paciente> {
        return RetrofitInstance.api.addPaciente(paciente)
    }

    suspend fun addMedico(medico: MedicoCadastro): Response<MedicoCadastro> {
        return RetrofitInstance.api.addMedico(medico)
    }

    suspend fun listTemas(): Response<List<Tema>>{
        return RetrofitInstance.api.listTemas()
    }

    suspend fun addPostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.addPostagem(postagem)
    }

    suspend fun listPostagem(): Response<List<Postagem>>{
        return RetrofitInstance.api.listPostagem()
    }
}