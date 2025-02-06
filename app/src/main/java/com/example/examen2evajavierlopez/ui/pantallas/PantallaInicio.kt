

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.examen2evajavierlopez.R


@Composable
fun PantallaInicio(modifier: Modifier) {

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