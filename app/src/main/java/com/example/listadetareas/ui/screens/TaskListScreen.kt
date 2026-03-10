package com.example.listadetareas.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listadetareas.domain.Task

@Composable
fun TaskListScreen() {

    val sampleTasks = listOf(
        Task(id = 1, title = "Ir a la UES"),
        Task(id = 2, title = "Hacer tarea de Moviles Android"),
        Task(id = 3, title = "Estudiar Diseño Responsivo")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(sampleTasks) { task ->
            Text(
                text = task.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

