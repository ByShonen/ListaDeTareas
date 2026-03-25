package com.example.listadetareas.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listadetareas.data.TaskViewModel
import com.example.listadetareas.ui.components.BottomNavigationBar
import com.example.listadetareas.ui.components.DrawerContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApp(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedTab by remember { mutableIntStateOf(0) }

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(onNavigateToMain = {
                navController.navigate("task_list") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("task_list") {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = { DrawerContent() }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(if (selectedTab == 0) "Tareas Pendientes" else "Tareas Completadas")
                            },
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menú")
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it }
                        )
                    },
                    floatingActionButton = {
                        if (selectedTab == 0) {
                            FloatingActionButton(onClick = { navController.navigate("add_task") }) {
                                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
                            }
                        }
                    }
                ) { innerPadding ->
                    val tasks by viewModel.tasks.collectAsState()
                    val filteredTasks = remember(tasks, selectedTab) {
                        tasks.filter { it.isDone == (selectedTab == 1) }
                    }
                    MainContentFiltered(
                        tasks = filteredTasks,
                        viewModel = viewModel,
                        onEditTask = { task -> navController.navigate("edit_task/${task.id}") },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composable("add_task") {
            AddTaskScreen(
                onSave = { title, description ->
                    viewModel.addTask(title, description)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "edit_task/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            val tasks by viewModel.tasks.collectAsState()
            val taskToEdit = tasks.find { it.id == taskId }
            taskToEdit?.let { task ->
                AddTaskScreen(
                    initialTitle = task.title,
                    initialDescription = task.description,
                    isEditMode = true,
                    onSave = { title, description ->
                        viewModel.updateTask(task.copy(title = title, description = description))
                        navController.popBackStack()
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun MainContentFiltered(
    tasks: List<com.example.listadetareas.domain.Task>,
    viewModel: TaskViewModel,
    onEditTask: (com.example.listadetareas.domain.Task) -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.foundation.layout.Box(modifier = modifier.fillMaxSize()) {
        TaskListScreenContent(
            tasks = tasks,
            onToggleDone = { viewModel.toggleTaskCompletion(it) },
            onEditTask = onEditTask
        )
    }
}
