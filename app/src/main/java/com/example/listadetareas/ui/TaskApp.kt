package com.example.listadetareas.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.listadetareas.data.TaskViewModel
import com.example.listadetareas.ui.components.BottomNavigationBar
import com.example.listadetareas.ui.components.DrawerContent
import com.example.listadetareas.ui.screens.TaskListScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApp(viewModel: TaskViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent()
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Lista de Tareas") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar()
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("add_task") }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar tarea"
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "task_list",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("task_list") {
                    TaskListScreen(viewModel = viewModel)
                }
                composable("add_task") {
                    AddTaskScreen(
                        viewModel = viewModel,
                        onTaskAdded = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}