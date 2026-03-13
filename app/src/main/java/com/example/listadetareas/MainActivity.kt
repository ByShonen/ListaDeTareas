package com.example.listadetareas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.listadetareas.data.AppDatabase
import com.example.listadetareas.data.TaskEntity
import com.example.listadetareas.ui.TaskApp
import com.example.listadetareas.ui.theme.ListaDeTareasTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "tasks_db"
        ).build()

        val dao = db.taskDao()

        lifecycleScope.launch {
            delay(1000)
            dao.insertTask(TaskEntity(title = "Prueba 1"))
            val items = dao.getTasks()
            Log.d("ROOM", "Tareas almacenadas: $items")
        }


        setContent {
            ListaDeTareasTheme {
                TaskApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListaDeTareasTheme {
        Greeting("Android")
    }
}
