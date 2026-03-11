package com.example.listadetareas.ui.components
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun BottomNavigationBar() {
    var selected by remember { mutableStateOf(0) }

    NavigationBar {
        NavigationBarItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            label = { Text(text = "Pendientes") },
            icon = { Icon(imageVector = Icons.Default.List, contentDescription = null) }
        )
        NavigationBarItem(
            selected = selected == 1,
            onClick = { selected = 1 },
            label = { Text(text = "Completadas") },
            icon = { Icon(imageVector = Icons.Default.Check, contentDescription = null) }
        )
    }
}