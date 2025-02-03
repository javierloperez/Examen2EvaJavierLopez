package com.example.examen2evajavierlopez.datos

import com.example.examen2evajavierlopez.dao.DaoBaseDatos
import com.example.examen2evajavierlopez.modelos.ObjetoJson
import com.example.examen2evajavierlopez.modelos.ObjetoLocal
import com.example.proyectofinal.conexiones.ParquesServicioApi



interface JsonRepositorio {
    suspend fun obtenerTodos(): List<ObjetoJson>
    suspend fun obtenerUno(id:Int): ObjetoJson
    suspend fun insertar(objeto: ObjetoJson):ObjetoJson
    suspend fun eliminar(id:Int):ObjetoJson
    suspend fun actualizar(id:Int,objeto: ObjetoJson):ObjetoJson
}

interface BaseLocalRepositorio {
    suspend fun obtenerTodosLocal(): List<ObjetoLocal>
    suspend fun obtenerUnoLocal(id:Int): ObjetoLocal
    suspend fun insertarLocal(objeto: ObjetoLocal)
    suspend fun actualizarLocal(favoritos: ObjetoLocal)
    suspend fun eliminarLocal(favoritos: ObjetoLocal)
}

class ConexionBaseLocalRepositorio(
    private val daoBaseDatos: DaoBaseDatos,
) : BaseLocalRepositorio {
    override suspend fun obtenerTodosLocal(): List<ObjetoLocal> =
        daoBaseDatos.obtenerTodosLocal()

    override suspend fun obtenerUnoLocal(id:Int): ObjetoLocal =
        daoBaseDatos.obtenerUnoLocal(id)

    override suspend fun insertarLocal(favoritos: ObjetoLocal) =
        daoBaseDatos.insertarLocal(favoritos)

    override suspend fun actualizarLocal(favoritos: ObjetoLocal) =
        daoBaseDatos.actualizarLocal(favoritos)

    override suspend fun eliminarLocal(favoritos: ObjetoLocal) =
        daoBaseDatos.eliminarLocal(favoritos)
}

class ConexionParquesRepositorio(
    private val servicioApi: ParquesServicioApi
) : JsonRepositorio {

    override suspend fun obtenerTodos(): List<ObjetoJson> =
        servicioApi.obtenerTodos()

    override suspend fun obtenerUno(id:Int): ObjetoJson =
        servicioApi.obtenerUno(id,)

    override suspend fun insertar(objeto: ObjetoJson): ObjetoJson =
        servicioApi.insertar(objeto)

    override suspend fun actualizar(id:Int, objeto: ObjetoJson): ObjetoJson =
        servicioApi.actualizar(id,objeto)

    override suspend fun eliminar(id:Int): ObjetoJson =
        servicioApi.eliminar(id)

}

