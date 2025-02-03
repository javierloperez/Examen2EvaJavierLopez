package com.example.proyectofinal.conexiones

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favoritos::class], version = 1, exportSchema = false)
abstract class BaseDatosLocal : RoomDatabase() {

    abstract fun daoBaseDatos(): DaoBaseDatos

    companion object {
        @Volatile
        private var Instance: BaseDatosLocal ? = null

        fun obtenerBaseDatos(context: Context): BaseDatosLocal {
            return Instance ?: synchronized(this) {                 //MODIFICAR NOMBRE BD
                Room.databaseBuilder(context, BaseDatosLocal::class.java, "Favoritos")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}