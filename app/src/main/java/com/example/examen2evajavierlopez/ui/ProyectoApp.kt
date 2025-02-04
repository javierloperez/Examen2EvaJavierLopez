package com.example.examen2evajavierlopez.ui

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examen2evajavierlopez.R
import com.example.examen2evajavierlopez.datos.HamburgerMenu
import com.example.examen2evajavierlopez.ui.pantallas.PantallaActualizar
import com.example.examen2evajavierlopez.ui.pantallas.PantallaInsertar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class Pantallas(@StringRes val titulo: Int) {
    Inicio(titulo = R.string.inicio),
    Insertar(titulo = R.string.insertar),
    Actualizar(titulo = R.string.actualizar)
}

val menu = arrayOf(
    HamburgerMenu(Icons.Filled.Home, Pantallas.Inicio.titulo, Pantallas.Inicio.name),

    )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProyectoApp(
    viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    val pilaRetroceso by navController.currentBackStackEntryAsState()
    val App = LocalContext.current as? Activity
    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
    )

    val uiState = viewModel.appUIstate


    var pantallaElegida by remember { mutableStateOf(Pantallas.Inicio) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    menu = menu,
                    pantallaActual = pantallaActual

                ) { ruta ->
                    coroutineScope.launch {
                        drawerState.close()

                    }
                    navController.navigate(ruta)
                }

            }
        },

        ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    pantallaActual = pantallaActual,
                    drawerState = drawerState,
                    navController = navController,
                    scrollBehavior = scrollBehavior

                )
            }, floatingActionButton = {

                FloatingActionButton(
                    onClick = { navController.navigate(route = Pantallas.Insertar.name) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.insertar),
                    )
                }
            }

        ) { innerPadding ->


            NavHost(
                navController = navController,
                startDestination = Pantallas.Inicio.name,
                modifier = Modifier.padding(innerPadding)
            ) {

                composable(route = Pantallas.Inicio.name) {
                    pantallaElegida = Pantallas.Inicio
                    /* PantallaInicio(
                         appUIState = uiStateParque,
                         onObtenerParques = { viewModel.obtenerParques() },
                         modifier = Modifier
                             .fillMaxSize(),
                         onEliminarParque = { viewModel.eliminarParque(it) },
                         onPulsarActualizar = {
                             viewModel.actualizarObjetoPulsado(it)
                             navController.navigate(Pantallas.Actualizar.name)
                         }
                     )*/
                }

                composable(route = Pantallas.Actualizar.name) {
                    PantallaActualizar(
                        modifier = Modifier
                            .fillMaxSize(),
                        objeto = viewModel.objetoPulsado,
                        onObjetoActualizado = {
                            viewModel.actualizarObjeto(it)
                            navController.popBackStack(pantallaElegida.name, inclusive = false)
                        }
                    )
                }
                composable(route = Pantallas.Insertar.name) {
                    PantallaInsertar(
                        tipo = pantallaElegida.titulo,
                        modifier = Modifier
                            .fillMaxSize(),
                        onObjetoInsertar = {
                            viewModel.insertarObjeto(it)
                            navController.popBackStack(pantallaElegida.name, inclusive = false)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DrawerContent(
    menu: Array<HamburgerMenu>,
    pantallaActual: Pantallas,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menu.forEach {
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = it.titulo)) },
                icon = { Icon(imageVector = it.icono, contentDescription = null) },
                selected = it.titulo == pantallaActual.titulo,
                onClick = {
                    onMenuClick(it.ruta)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    drawerState: DrawerState?,
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    var mostrarMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (drawerState != null) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = stringResource(id = R.string.atras)
                    )
                }
            }
        },
        actions = {

            IconButton(onClick = { mostrarMenu = true }) {
                Icon(
                    imageVector = Icons.Outlined.MoreVert,
                    contentDescription = stringResource(R.string.atras)
                )
            }
            DropdownMenu(
                expanded = mostrarMenu,
                onDismissRequest = { mostrarMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "aguela") },
                    onClick = {
                        mostrarMenu = false
                        navController.navigate(Pantallas.Inicio.name)
                    }
                )
            }

        },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}