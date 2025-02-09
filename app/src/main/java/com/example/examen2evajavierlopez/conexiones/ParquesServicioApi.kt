package com.example.proyectofinal.conexiones

import com.example.examen2evajavierlopez.modelos.ObjetoJson
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ParquesServicioApi {
    @GET("")
    suspend fun obtenerTodos(): List<ObjetoJson>

    @GET("/{id}")
    suspend fun obtenerUno(
    @Path("id") id:String
    ):ObjetoJson

    @POST("")
    suspend fun insertar(
        @Body parque: ObjetoJson
    ): ObjetoJson

    @PUT("/{id}")
    suspend fun actualizar(
        @Path("id") id: String,
        @Body parques: ObjetoJson
    ): ObjetoJson

    @DELETE("/{id}")
    suspend fun eliminar(
        @Path("id") id: String
    ):ObjetoJson





}