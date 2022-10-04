package com.generation.desenvolvmed.api

import com.generation.desenvolvmed.model.Medico
import com.generation.desenvolvmed.model.Paciente
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("paciente/busca-email/{email}")
    suspend fun getCadastroPacienteByEmail(@Path("email") email: String): Response<Paciente>

    @GET("medico/busca-email/{email}")
    suspend fun getCadastroMedicoByEmail(@Path("email") email: String): Response<Medico>

    @POST("cadastro/paciente")
    suspend fun addPaciente(
        @Body paciente: Paciente
    ): Response<Paciente>

    @POST("cadastro/medico")
    suspend fun addMedico(
        @Body medico: Medico
    ): Response<Medico>

}