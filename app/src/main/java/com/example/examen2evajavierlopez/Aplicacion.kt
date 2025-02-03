package com.example.examen2evajavierlopez

import android.app.Application
import com.example.examen2evajavierlopez.datos.ContenedorApp
import com.example.examen2evajavierlopez.datos.ContenedorAppGeneral


class Aplicacion: Application() {
    lateinit var contenedor: ContenedorApp
    override fun onCreate() {
        super.onCreate()
        contenedor = ContenedorAppGeneral(this)
    }
}