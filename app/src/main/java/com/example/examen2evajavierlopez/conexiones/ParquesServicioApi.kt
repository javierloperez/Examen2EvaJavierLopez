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
    @GET("parques")
    suspend fun obtenerTodos(): List<ObjetoJson>

    @GET("parques/{id}")
    suspend fun obtenerUno(
    @Path("id") id:Int
    ):ObjetoJson

    @POST("parques")
    suspend fun insertar(
        @Body parque: ObjetoJson
    ): ObjetoJson

    @PUT("parques/{id}")
    suspend fun actualizar(
        @Path("id") id: Int,
        @Body parques: ObjetoJson
    ): ObjetoJson

    @DELETE("parques/{id}")
    suspend fun eliminar(
        @Path("id") id: Int
    ):ObjetoJson





}