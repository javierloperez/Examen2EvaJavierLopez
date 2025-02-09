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
import com.example.examen2evajavierlopez.modelos.ObjetoLocal

@Composable
fun PantallaActualizar(modifier: Modifier, objeto: Any?, onObjetoActualizado: (Any) -> Unit) {

    when(objeto){
        is ObjetoJson -> ActualizarJson(
            objeto = objeto,
            onObjetoActualizado = onObjetoActualizado
        )
        is ObjetoLocal -> ActualizarLocal(
            objeto = objeto,
            onObjetoActualizado = onObjetoActualizado
        )
    }

}

@Composable
private fun ActualizarJson(objeto: ObjetoJson, onObjetoActualizado: (Any) -> Unit) {

    var errorNombre by remember { mutableStateOf(false) }
    var errorDescripcion by remember { mutableStateOf(false) }
    var errorTipo by remember { mutableStateOf(false) }
    var marcarError by remember { mutableStateOf(false) }
//    var nombre by remember { mutableStateOf(objeto.nombre) }
//    var descripcion by remember { mutableStateOf(objeto.descripcion) }
//    var tipo by remember { mutableStateOf(objeto.tipo) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {

        TextField(
            value = objeto.id.toString(),
            label = {
                Text(stringResource(R.string.id))
            },
            onValueChange = {},
            enabled = false
        )


       /* Spacer(Modifier.height(16.dp))

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

        Spacer(Modifier.height(16.dp))
        */

        if (errorNombre || errorDescripcion || errorTipo) {
            marcarError = true
        } else {
            marcarError = false
        }
        Button(
            onClick = {
                val objetoActualizado = ObjetoJson("","")
                onObjetoActualizado(objetoActualizado)
            },
            enabled = !marcarError
        ) {
            Text(
                stringResource(R.string.actualizar),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}


@Composable
private fun ActualizarLocal(objeto: ObjetoLocal, onObjetoActualizado: (Any) -> Unit) {

    var errorNombre by remember { mutableStateOf(false) }
    var errorDescripcion by remember { mutableStateOf(false) }
    var errorTipo by remember { mutableStateOf(false) }
    var marcarError by remember { mutableStateOf(false) }
//    var nombre by remember { mutableStateOf(objeto.nombre) }
//    var descripcion by remember { mutableStateOf(objeto.descripcion) }
//    var tipo by remember { mutableStateOf(objeto.tipo) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {

        TextField(
            value = objeto.id.toString(),
            label = {
                Text(stringResource(R.string.id))
            },
            onValueChange = {},
            enabled = false
        )


        /* Spacer(Modifier.height(16.dp))

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

         Spacer(Modifier.height(16.dp))
         */

        if (errorNombre || errorDescripcion || errorTipo) {
            marcarError = true
        } else {
            marcarError = false
        }
        Button(
            onClick = {
                val objetoActualizado = ObjetoLocal(0,"")
                onObjetoActualizado(objetoActualizado)
            },
            enabled = !marcarError
        ) {
            Text(
                stringResource(R.string.actualizar),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }
}

