package com.example.examen2evajavierlopez.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.examen2evajavierlopez.R
import com.example.examen2evajavierlopez.modelos.ObjetoJson

@Composable

fun PantallaInsertar(tipo: Int, modifier: Modifier, onObjetoInsertar: (Any) -> Unit) {

    when (tipo) {
        R.string.inicio -> InsertarJson(onObjetoInsertar = onObjetoInsertar)
    }
}

@Composable
private fun InsertarJson(onObjetoInsertar: (Any) -> Unit) {

    var errorNombre by remember { mutableStateOf(false) }
    var deshabilitar by remember { mutableStateOf(true) }
    var errorDescripcion by remember { mutableStateOf(false) }
    var errorTipo by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Spacer(Modifier.height(16.dp))
/*
        TextField(
            value = nombre,
            label = { Text(stringResource(R.string.nombre)) },
            onValueChange = {
                nombre = it
                if (it.isBlank()) {
                    errorNombre = true
                } else {
                    errorNombre = false
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = errorNombre

        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = descripcion,
            label = { Text(stringResource(R.string.descripcion)) },
            onValueChange = {
                descripcion = it
                if (it.isBlank()) {
                    errorDescripcion = true
                } else {
                    errorDescripcion = false
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = errorDescripcion
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = tipo,
            label = { Text(stringResource(R.string.tipo)) },
            onValueChange = {
                tipo = it
                if (it.isBlank()) {
                    errorTipo = true
                } else {
                    errorTipo = false
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = errorTipo

        )
 */
        Spacer(Modifier.height(16.dp))
        if (errorNombre || errorDescripcion || errorTipo) {
            deshabilitar = true
        } else {
            deshabilitar = false
        }
        Button(
            onClick = {
                val objetoNuevo = ObjetoJson("","")
                onObjetoInsertar(objetoNuevo)

            },
            enabled = !deshabilitar
        ) {

            Text(
                stringResource(R.string.insertar),
                style = MaterialTheme.typography.bodyLarge            )
        }

    }
}