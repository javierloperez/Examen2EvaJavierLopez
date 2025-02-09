import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.examen2evajavierlopez.R
import com.example.examen2evajavierlopez.modelos.ObjetoJson
import com.example.examen2evajavierlopez.ui.AppUIstateJson


@Composable
fun PantallaInicio(
    modifier: Modifier,
    appUIState: AppUIstateJson,
    onObtenerJson: () -> Unit,
    onEliminarJson: (String) -> Unit,
    onPulsarActualizar: (ObjetoJson) -> Unit
) {

    when (appUIState) {
        is AppUIstateJson.ObtenerTodosExitoJson -> ListarJson(
            appUIState.objeto,
            onEliminarJson = onEliminarJson,
            onPulsarActualizar = onPulsarActualizar
        )

        is AppUIstateJson.Error -> PantallaError(Modifier)
        is AppUIstateJson.Cargando -> PantallaCargando(Modifier)
        is AppUIstateJson.ActualizarExito -> onObtenerJson()
        is AppUIstateJson.EliminarExito -> onObtenerJson()
        is AppUIstateJson.CrearExito -> onObtenerJson()
        else -> {

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListarJson(
    lista: List<ObjetoJson>,
    onEliminarJson: (String) -> Unit,
    onPulsarActualizar: (ObjetoJson) -> Unit
) {

    var idObjeto by remember { mutableStateOf("") }
    var eliminarDialogo by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),

        ) {
        items(lista) { objeto ->
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(
                        onClick = { onPulsarActualizar(objeto) },
                        onLongClick = {
                            idObjeto = objeto.id
                            eliminarDialogo = true
                        }
                    )
                    .border(width = 2.dp, color = Color.Black)
                    .aspectRatio(3.6f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
//                    Text(stringResource(R.string.nombre) + ": " + objeto.nombre)
//                    Spacer(Modifier.height(5.dp))
//                    Text(stringResource(R.string.descripcion) + ": " + objeto.descripcion)
//                    Spacer(Modifier.height(5.dp))
//                    Text(stringResource(R.string.tipo) + ": " + objeto.tipo)
                }
            }
        }
    }
    if (eliminarDialogo) {
        AlertDialog(
            onDismissRequest = {
                eliminarDialogo = false
            },
            title = { Text(text = stringResource(R.string.tituloEliminar)) },
            dismissButton = {
                TextButton(
                    onClick = {
                        eliminarDialogo = false
                    }) {
                    Text(text = stringResource(R.string.cancelar))
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onEliminarJson(idObjeto)
                }) {
                    Text(text = stringResource(R.string.aceptar))
                }
            },
        )
    }
}

@Composable
fun PantallaCargando(modifier: Modifier) {

    Image(
        modifier = modifier.fillMaxSize(),
        painter = painterResource(R.drawable.cargando),
        contentDescription = stringResource(R.string.cargando)
    )
}

@Composable
fun PantallaError(modifier: Modifier) {
    Image(
        modifier = modifier.fillMaxSize(),
        painter = painterResource(R.drawable.error),
        contentDescription = stringResource(R.string.error)
    )
}