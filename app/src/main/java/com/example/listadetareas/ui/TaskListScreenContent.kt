package com.example.listadetareas.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listadetareas.domain.Task

@Composable
fun TaskListScreenContent(
    tasks: List<Task>,
    onToggleDone: (Task) -> Unit,
    onEditTask: (Task) -> Unit
) {
    if (tasks.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "No hay tareas en esta sección",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onToggleDone = { onToggleDone(task) },
                    onEdit = { onEditTask(task) }
                )
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onToggleDone: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = if (task.isDone) Icons.Default.CheckCircle else Icons.Default.Warning,
                    contentDescription = if (task.isDone) "Completada" else "Pendiente",
                    tint = if (task.isDone) Color(0xFF4CAF50) else Color(0xFFFF9800)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = task.title, style = MaterialTheme.typography.titleMedium)
                    if (task.description.isNotBlank()) {
                        Text(
                            text = task.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }
                }
            }
            Row {
                IconButton(onClick = onToggleDone) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Cambiar estado",
                        tint = if (task.isDone) Color(0xFF4CAF50) else Color.LightGray
                    )
                }
                IconButton(onClick = onEdit, enabled = !task.isDone) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = if (task.isDone) Color.LightGray else MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
