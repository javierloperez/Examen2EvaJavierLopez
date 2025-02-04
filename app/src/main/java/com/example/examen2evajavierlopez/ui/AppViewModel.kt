package com.example.examen2evajavierlopez.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.examen2evajavierlopez.Aplicacion
import com.example.examen2evajavierlopez.datos.BaseLocalRepositorio
import com.example.examen2evajavierlopez.datos.JsonRepositorio
import com.example.examen2evajavierlopez.modelos.ObjetoJson
import com.example.examen2evajavierlopez.modelos.ObjetoLocal
import kotlinx.coroutines.launch

sealed interface AppUIstate {
    data class ObtenerTodosExitoJson(val objeto: List<ObjetoJson>) : AppUIstate
    data class ObtenerUnoExitoJson(val objeto: ObjetoJson) : AppUIstate
    data class ObtenerTodosExitoLocal(val objeto: List<ObjetoLocal>) : AppUIstate
    data class ObtenerUnoExitoLocal(val objeto: ObjetoLocal) : AppUIstate



    object CrearExito : AppUIstate
    object Cargando : AppUIstate
    object Error : AppUIstate
    object ActualizarExito : AppUIstate
    object EliminarExito : AppUIstate

}


class AppViewModel(
    private val baseLocalRepositorio: BaseLocalRepositorio,
    private val jsonRepositorio: JsonRepositorio
) : ViewModel() {

    var appUIstate: AppUIstate by mutableStateOf(AppUIstate.Cargando)
        private set


    var objetoPulsado: Any? by mutableStateOf(null)
        private set


    fun <T> actualizarObjetoPulsado(objeto: T) {
        objetoPulsado = objeto
    }

    init {
        //obtenerJson()

    }

    fun actualizarObjeto(objeto: Any) {

        viewModelScope.launch {
            try {
                when (objeto) {
                    is ObjetoJson -> {
                        jsonRepositorio.actualizar(objeto.id, objeto)
                        appUIstate = AppUIstate.ActualizarExito
                    }

                    is ObjetoLocal -> {
                        baseLocalRepositorio.actualizarLocal(objeto)
                        appUIstate = AppUIstate.ActualizarExito
                    }

                }

            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }

    fun insertarObjeto(objeto: Any) {

        viewModelScope.launch {
            try {
                when (objeto) {
                    is ObjetoJson -> {
                        jsonRepositorio.insertar(objeto)
                        appUIstate = AppUIstate.CrearExito
                    }

                    is ObjetoLocal -> {
                        baseLocalRepositorio.insertarLocal(objeto)
                        appUIstate = AppUIstate.CrearExito
                    }
                }

            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }
    fun obtenerTodosJson() {
        viewModelScope.launch {
            appUIstate = AppUIstate.Cargando
            appUIstate = try {
                val lista = jsonRepositorio.obtenerTodos()
                AppUIstate.ObtenerTodosExitoJson(lista)
            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }

    fun obtenerUnoJson(id : Int) {
        viewModelScope.launch {
            appUIstate = AppUIstate.Cargando
            appUIstate = try {
                val objeto = jsonRepositorio.obtenerUno(id)
                AppUIstate.ObtenerUnoExitoJson(objeto)
            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }


    fun eliminarJson(id: Int) {
        viewModelScope.launch {
            appUIstate = try {
                jsonRepositorio.eliminar(id)
                AppUIstate.EliminarExito
            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }


    fun obtenerTodosLocal() {
        viewModelScope.launch {
            try {
                val lista = baseLocalRepositorio.obtenerTodosLocal()
                AppUIstate.ObtenerTodosExitoLocal(lista)
            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }

    fun obtenerUnoLocal(id : Int) {
        viewModelScope.launch {
            try {
                val objeto = baseLocalRepositorio.obtenerUnoLocal(id)
                AppUIstate.ObtenerUnoExitoLocal(objeto)
            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }


    fun eliminarLocal(objetoLocal: ObjetoLocal) {
        viewModelScope.launch {
            appUIstate = try {
                baseLocalRepositorio.eliminarLocal(objetoLocal)
                AppUIstate.EliminarExito
            } catch (e: Exception) {
                AppUIstate.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as Aplicacion)
                val jsonRepositorio = aplicacion.contenedor.jsonRepositorio
                val baseLocalRepositorio = aplicacion.contenedor.baseLocalRepositorio
                AppViewModel(
                    jsonRepositorio = jsonRepositorio,
                    baseLocalRepositorio = baseLocalRepositorio
                )
            }
        }
    }
}



