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

    suspend fun getCadastroByEmail(email: String): Response<Cadastro>{
        return RetrofitInstance.api.getCadastroByEmail(email)
    }

    suspend fun addPaciente(paciente: PacienteCadastro): Response<PacienteCadastro> {
        return RetrofitInstance.api.addPaciente(paciente)
    }

    suspend fun addMedico(medico: MedicoCadastro): Response<MedicoCadastro> {
        return RetrofitInstance.api.addMedico(medico)
    }

    suspend fun attPaciente(paciente: PacienteCadastro): Response<PacienteCadastro> {
        return RetrofitInstance.api.attPaciente(paciente)
    }

    suspend fun attMedico(medico: MedicoCadastro): Response<MedicoCadastro> {
        return RetrofitInstance.api.attMedico(medico)
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