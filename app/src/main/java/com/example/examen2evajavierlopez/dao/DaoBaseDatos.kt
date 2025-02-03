package com.example.examen2evajavierlopez.dao

import androidx.room.Dao
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examen2evajavierlopez.modelos.ObjetoLocal;
@Dao
interface DaoBaseDatos {
    @Query("select * from ObjetoLocal where id = :id")
    suspend fun obtenerUnoLocal(id:Int): ObjetoLocal

    @Query("select * from ObjetoLocal order by id DESC")
    suspend fun obtenerTodosLocal(): List<ObjetoLocal>

    @Insert
    suspend fun insertarLocal(objeto: ObjetoLocal)

    @Update
    suspend fun actualizarLocal(objeto: ObjetoLocal)

    @Delete
    suspend fun eliminarLocal(objeto:ObjetoLocal)
}