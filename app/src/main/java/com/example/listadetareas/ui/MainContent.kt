package com.example.listadetareas.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.listadetareas.data.TaskViewModel
import com.example.listadetareas.ui.screens.TaskListScreen

@Composable
fun MainContent(modifier: Modifier = Modifier, viewModel: TaskViewModel) {
    Box(modifier = modifier.fillMaxSize()) {
        TaskListScreen(viewModel = viewModel)
    }
}
