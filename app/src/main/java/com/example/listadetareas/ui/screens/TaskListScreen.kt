package com.example.listadetareas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.listadetareas.data.TaskViewModel
import com.example.listadetareas.domain.Task

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(tasks) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Row {
                    Checkbox(
                        checked = task.isDone,
                        onCheckedChange = { viewModel.toggleTaskCompletion(task) }
                    )
                    TextButton(onClick = { viewModel.deleteTask(task) }) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
