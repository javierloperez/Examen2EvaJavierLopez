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

sealed interface AppUIstateJson {
    data class ObtenerTodosExitoJson(val objeto: List<ObjetoJson>) : AppUIstateJson
    data class ObtenerUnoExitoJson(val objeto: ObjetoJson) : AppUIstateJson




    object CrearExito : AppUIstateJson
    object Cargando : AppUIstateJson
    object Error : AppUIstateJson
    object ActualizarExito : AppUIstateJson
    object EliminarExito : AppUIstateJson

}

sealed interface AppUIstateLocal {
    data class ObtenerTodosExitoLocal(val objeto: List<ObjetoLocal>) : AppUIstateLocal
    data class ObtenerUnoExitoLocal(val objeto: ObjetoLocal) : AppUIstateLocal



    object CrearExito : AppUIstateLocal
    object Cargando : AppUIstateLocal
    object Error : AppUIstateLocal
    object ActualizarExito : AppUIstateLocal
    object EliminarExito : AppUIstateLocal

}


class AppViewModel(
    private val baseLocalRepositorio: BaseLocalRepositorio,
    private val jsonRepositorio: JsonRepositorio
) : ViewModel() {

    var appUIstateJson: AppUIstateJson by mutableStateOf(AppUIstateJson.Cargando)
        private set

    var appUIstateLocal: AppUIstateLocal by mutableStateOf(AppUIstateLocal.Cargando)
        private set


    var objetoPulsado: Any? by mutableStateOf(null)
        private set


    fun <T> actualizarObjetoPulsado(objeto: T) {
        objetoPulsado = objeto
    }

    init {
        obtenerTodosJson()
    }

    fun actualizarObjeto(objeto: Any) {

        viewModelScope.launch {
            try {
                when (objeto) {
                    is ObjetoJson -> {
                        jsonRepositorio.actualizar(objeto.id, objeto)
                        appUIstateJson = AppUIstateJson.ActualizarExito
                    }

                    is ObjetoLocal -> {
                        baseLocalRepositorio.actualizarLocal(objeto)
                        appUIstateLocal = AppUIstateLocal.ActualizarExito
                    }

                }

            } catch (e: Exception) {
                AppUIstateJson.Error
            }
        }
    }

    fun insertarObjeto(objeto: Any) {

        viewModelScope.launch {
            try {
                when (objeto) {
                    is ObjetoJson -> {
                        jsonRepositorio.insertar(objeto)
                        appUIstateJson = AppUIstateJson.CrearExito
                    }

                    is ObjetoLocal -> {
                        baseLocalRepositorio.insertarLocal(objeto)
                        appUIstateLocal = AppUIstateLocal.CrearExito
                    }
                }

            } catch (e: Exception) {
                AppUIstateJson.Error
            }
        }
    }
    fun obtenerTodosJson() {
        viewModelScope.launch {
            appUIstateJson = AppUIstateJson.Cargando
            appUIstateJson = try {
                val lista = jsonRepositorio.obtenerTodos()
                AppUIstateJson.ObtenerTodosExitoJson(lista)
            } catch (e: Exception) {
                AppUIstateJson.Error
            }
        }
    }

    fun obtenerUnoJson(id : String) {
        viewModelScope.launch {
            appUIstateJson = AppUIstateJson.Cargando
            appUIstateJson = try {
                val objeto = jsonRepositorio.obtenerUno(id)
                AppUIstateJson.ObtenerUnoExitoJson(objeto)
            } catch (e: Exception) {
                AppUIstateJson.Error
            }
        }
    }


    fun eliminarJson(id: String) {
        viewModelScope.launch {
            appUIstateJson = try {
                jsonRepositorio.eliminar(id)
                AppUIstateJson.EliminarExito
            } catch (e: Exception) {
                AppUIstateJson.Error
            }
        }
    }


    fun obtenerTodosLocal() {
        viewModelScope.launch {
           appUIstateLocal = try {
                val lista = baseLocalRepositorio.obtenerTodosLocal()
                AppUIstateLocal.ObtenerTodosExitoLocal(lista)
            } catch (e: Exception) {
                AppUIstateLocal.Error
            }
        }
    }

    fun obtenerUnoLocal(id : Int) {
        viewModelScope.launch {
            appUIstateLocal = try {
                val objeto = baseLocalRepositorio.obtenerUnoLocal(id)
                AppUIstateLocal.ObtenerUnoExitoLocal(objeto)
            } catch (e: Exception) {
                AppUIstateLocal.Error
            }
        }
    }


    fun eliminarLocal(objetoLocal: ObjetoLocal) {
        viewModelScope.launch {
            appUIstateLocal = try {
                baseLocalRepositorio.eliminarLocal(objetoLocal)
                AppUIstateLocal.EliminarExito
            } catch (e: Exception) {
                AppUIstateLocal.Error
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



