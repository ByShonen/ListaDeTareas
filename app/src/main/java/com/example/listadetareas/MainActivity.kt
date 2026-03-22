package com.example.listadetareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.room.Room
import com.example.listadetareas.data.AppDatabase
import com.example.listadetareas.data.TaskViewModel
import com.example.listadetareas.data.TaskViewModelFactory
import com.example.listadetareas.data.repository.TaskRepositoryImpl
import com.example.listadetareas.ui.TaskApp
import com.example.listadetareas.ui.theme.ListaDeTareasTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tasks_db"
        ).build()
    }

    private val repository by lazy { TaskRepositoryImpl(db.taskDao()) }

    private val viewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaDeTareasTheme {
                TaskApp(viewModel)
            }
        }
    }
}