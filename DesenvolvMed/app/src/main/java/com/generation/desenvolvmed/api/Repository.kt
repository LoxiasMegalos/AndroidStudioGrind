package com.generation.desenvolvmed.api

import com.generation.desenvolvmed.model.Medico
import com.generation.desenvolvmed.model.Paciente
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

    suspend fun addMedico(medico: Medico): Response<Medico> {
        return RetrofitInstance.api.addMedico(medico)
    }
}