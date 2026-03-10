package com.example.listadetareas.domain
data class Task(
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val isDone: Boolean = false
)
